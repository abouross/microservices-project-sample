package aboudev.microservicesprojectsample.inventory.repositories;

import aboudev.microservicesprojectsample.inventory.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findALLBySkuCodeIn(List<String> skuCodes);
}
