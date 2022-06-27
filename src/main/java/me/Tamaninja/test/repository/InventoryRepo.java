package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT i.* FROM inventory i WHERE i.name = :inventoryName", nativeQuery = true)
    Optional<Inventory> findByName(@Param("inventoryName") String inventoryName);
}
