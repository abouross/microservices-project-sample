package aboudev.microservicesprojectsample.inventory.services;

import aboudev.microservicesprojectsample.inventory.repositories.InventoryRepository;
import aboudev.microservicesprojectsample.inventory.web.dtos.InventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;

    public List<InventoryDto> isInStock(List<String> skuCodes) {
        return repository.findALLBySkuCodeIn(skuCodes)
                .stream()
                .map(inventory -> InventoryDto.builder()
                        .skuCode(inventory.getSkuCode())
                        .quantity(inventory.getQuantity())
                        .isInStock(inventory.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }
}
