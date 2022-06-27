package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContent;
import me.Tamaninja.test.entity.PalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PalletContentRepo extends JpaRepository<PalletContent, Integer> {


    @Query(value = "SELECT pc.*, COUNT(p.pallet_content_name) AS used " +
            "FROM pallet p, pallet_content pc " +
            "WHERE p.pallet_content_name = pc.name " +
            "AND p.origin_id = :origin " +
            "GROUP BY pc.id ORDER BY used DESC LIMIT 1", nativeQuery = true)
    PalletContent mostUsedContent(@Param("origin") Long originId);
}