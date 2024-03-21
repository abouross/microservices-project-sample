package aboudev.microservicesprojectsample.product.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class ProductDto {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
}
