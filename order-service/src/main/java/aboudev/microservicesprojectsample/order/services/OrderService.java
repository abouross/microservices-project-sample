package aboudev.microservicesprojectsample.order.services;

import aboudev.microservicesprojectsample.order.entities.Order;
import aboudev.microservicesprojectsample.order.entities.OrderItem;
import aboudev.microservicesprojectsample.events.OrderPlacedEvent;
import aboudev.microservicesprojectsample.order.proxies.InventoryProxy;
import aboudev.microservicesprojectsample.order.repositories.OrderRepository;
import aboudev.microservicesprojectsample.order.web.dtos.InventoryDto;
import aboudev.microservicesprojectsample.order.web.forms.OrderForm;
import aboudev.microservicesprojectsample.order.web.forms.OrderItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final InventoryProxy inventoryProxy;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderForm form) {
        Order order = Order.builder()
                .OrderNumber(UUID.randomUUID().toString())
                .OrderLines(
                        form.getOrderItems()
                                .stream()
                                .map(
                                        orderItemForm -> OrderItem.builder()
                                                .skuCode(orderItemForm.getSkuCode())
                                                .price(orderItemForm.getPrice())
                                                .quantity(orderItemForm.getQuantity())
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
        InventoryDto[] inventory = inventoryProxy.isInStock(
                form.getOrderItems()
                        .stream()
                        .map(OrderItemForm::getSkuCode)
                        .collect(Collectors.toList())
        );
        boolean allProductsInStock = Arrays.stream(inventory)
                .allMatch(InventoryDto::isInStock);

        if (!allProductsInStock)
            throw new RuntimeException("Product is not in stock, please try again later.");
        repository.save(order);
        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
    }
}
