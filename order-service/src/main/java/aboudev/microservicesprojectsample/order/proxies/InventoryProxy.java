package aboudev.microservicesprojectsample.order.proxies;

import aboudev.microservicesprojectsample.order.web.dtos.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service", path = "/inventories")
public interface InventoryProxy {
    @GetMapping("/is-in-stock")
    InventoryDto[] isInStock(@RequestParam List<String> skuCodes);
}
