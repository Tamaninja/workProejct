package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletContainerRepository extends JpaRepository<PalletContainer,Integer> {


    @Query(value = "SELECT pc.*, COUNT(p.pallet_container_name) AS used " +
            "FROM pallet p, pallet_container pc " +
            "WHERE p.pallet_container_name = pc.name " +
            "AND p.origin_id = :origin " +
            "GROUP BY pc.id ORDER BY used DESC LIMIT 1", nativeQuery = true)
    PalletContainer mostUsedContainer(@Param("origin") Long originId);
}
