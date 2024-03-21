package aboudev.microservicesprojectsample.product.repositories;

import aboudev.microservicesprojectsample.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
