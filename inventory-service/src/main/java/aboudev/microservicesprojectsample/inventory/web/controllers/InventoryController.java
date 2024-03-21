package aboudev.microservicesprojectsample.inventory.web.controllers;

import aboudev.microservicesprojectsample.inventory.services.InventoryService;
import aboudev.microservicesprojectsample.inventory.web.dtos.InventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping("/is-in-stock")
    public List<InventoryDto> isInStock(@RequestParam List<String> skuCodes) {
        return service.isInStock(skuCodes);
    }
}
