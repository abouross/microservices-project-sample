package aboudev.microservicesprojectsample.inventory.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InventoryDto {
    private String skuCode;
    private Integer quantity;
    private boolean isInStock;
}
