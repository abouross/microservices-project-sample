package aboudev.microservicesprojectsample.inventory;

import aboudev.microservicesprojectsample.inventory.entities.Inventory;
import aboudev.microservicesprojectsample.inventory.repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository repository) {
        return args -> {
            repository.save(
                    Inventory.builder()
                            .skuCode("galaxy_s23_ultra")
                            .quantity(100)
                            .build()
            );

            repository.save(
                    Inventory.builder()
                            .skuCode("iphone_14")
                            .quantity(0)
                            .build()
            );
        };
    }
}
