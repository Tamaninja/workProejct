package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContainer;
import me.Tamaninja.test.entity.PalletContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PalletContentRepository extends JpaRepository<PalletContent, String> {


    @Query(value = "SELECT pc.* " +
            "FROM pallet p, pallet_content pc " +
            "WHERE p.pallet_content_id = pc.id " +
            "AND p.origin_id = :origin " +
            "GROUP BY pc.id " +
            "ORDER BY COUNT(p.pallet_content_id) DESC " +
            "LIMIT 1;", nativeQuery = true)
    PalletContent mostUsedContent(@Param("origin") Long originId);

    @Query(value = "SELECT * " +
            "FROM pallet_content ", nativeQuery = true)
    List<PalletContent> getAllPalletContents();
}