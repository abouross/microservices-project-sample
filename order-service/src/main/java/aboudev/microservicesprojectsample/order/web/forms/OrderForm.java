package aboudev.microservicesprojectsample.order.web.forms;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderForm {
    @NotNull
    @NotEmpty
    private List<@Valid OrderItemForm> orderItems;
}
