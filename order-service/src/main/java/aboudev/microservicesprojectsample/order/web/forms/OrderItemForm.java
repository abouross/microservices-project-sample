package aboudev.microservicesprojectsample.order.web.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemForm {
    @NotBlank
    private String skuCode;
    @Min(0)
    @NotNull
    private BigDecimal price;
    @Min(1)
    @NotNull
    private Integer quantity;
}
