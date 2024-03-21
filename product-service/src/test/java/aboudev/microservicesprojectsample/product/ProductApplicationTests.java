package aboudev.microservicesprojectsample.product;

import aboudev.microservicesprojectsample.product.repositories.ProductRepository;
import aboudev.microservicesprojectsample.product.web.forms.ProductForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@Slf4j
@AutoConfigureMockMvc
public class ProductApplicationTests {
    @Container
    static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:8")
            .withDatabaseName("product-service")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        log.info("Database url {}", mySQLContainer.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.database.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.database.password", mySQLContainer::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateProduct() throws Exception {
        ProductForm form = ProductForm.builder()
                .name("Galaxy S23 Ultra")
                .description("Samsung Galaxy S23 Ultra")
                .price(BigDecimal.valueOf(550000))
                .build();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form))
        ).andExpect(status().isCreated());
        Assertions.assertEquals(1, repository.count());
    }
}
