package aboudev.microservicesprojectsample.product.web.forms;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductForm {
    @NotBlank
    private String name;
    private String description;
    @Min(0)
    private BigDecimal price;
}
