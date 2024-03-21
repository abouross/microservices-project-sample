package aboudev.microservicesprojectsample.product.services;

import aboudev.microservicesprojectsample.product.entities.Product;
import aboudev.microservicesprojectsample.product.repositories.ProductRepository;
import aboudev.microservicesprojectsample.product.web.dtos.ProductDto;
import aboudev.microservicesprojectsample.product.web.forms.ProductForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public void create(ProductForm form) {
        Product product = repository.save(Product.builder()
                .name(form.getName())
                .description(form.getDescription())
                .price(form.getPrice())
                .build());
        log.info("Product {} saved", product);
    }

    public List<ProductDto> getAllProducts() {
        return repository.findAll()
                .stream().map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
