package aboudev.microservicesprojectsample.order.web.controllers;

import aboudev.microservicesprojectsample.order.services.OrderService;
import aboudev.microservicesprojectsample.order.web.forms.OrderForm;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallback")
    public String placeOrder(@RequestBody @Valid OrderForm form) {
        service.placeOrder(form);
        return "Order place successfully";
    }


    public String placeOrderFallback(RuntimeException exception) {
        log.error("Place order exception {}", exception.toString());
        return "Oops something went wrong, please try later.";
    }
}
