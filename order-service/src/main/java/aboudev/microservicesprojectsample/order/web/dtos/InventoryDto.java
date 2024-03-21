package aboudev.microservicesprojectsample.order.web.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDto {
    private String skuCode;
    private Integer quantity;
    private boolean isInStock;
}
