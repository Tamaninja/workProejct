package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PalletContainerRepository extends JpaRepository<PalletContainer,String> {


    @Query(value = "SELECT pc.* " +
            "FROM pallet p, pallet_content pc " +
            "WHERE p.pallet_content_identifier = pc.identifier " +
            "AND p.origin_id = :origin " +
            "GROUP BY pc.identifier " +
            "ORDER BY COUNT(p.pallet_content_identifier) DESC " +
            "LIMIT 1; ", nativeQuery = true)
    PalletContainer mostUsedContainer(@Param("origin") Long originId);

    @Query(value = "SELECT * " +
            "FROM pallet_container " +
            "WHERE pallet_container.default_amount = 1", nativeQuery = true)
    List<PalletContainer> findAllPalletTypes();

    @Query(value = "SELECT * " +
            "FROM pallet_container " +
            "WHERE NOT pallet_container.default_amount = 1", nativeQuery = true)
    List<PalletContainer> findAllPalletContainers();

}
