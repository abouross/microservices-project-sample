package aboudev.microservicesprojectsample.product.web.controllers;

import aboudev.microservicesprojectsample.product.services.ProductService;
import aboudev.microservicesprojectsample.product.web.dtos.ProductDto;
import aboudev.microservicesprojectsample.product.web.forms.ProductForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ProductForm form) {
        service.create(form);
    }

    @GetMapping
    public List<ProductDto> index() {
        return service.getAllProducts();
    }
}
