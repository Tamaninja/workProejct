package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PalletTypeRepo extends JpaRepository<PalletType,Integer> {


    @Query(value = "SELECT pt.*, COUNT(p.pallet_type_name) AS used " +
            "FROM pallet p, pallet_type pt " +
            "WHERE p.pallet_type_name = pt.name " +
            "AND p.location_name = :locName " +
            "GROUP BY pt.id ORDER BY used DESC LIMIT 1", nativeQuery = true)
    Optional<PalletType> suggestPalletType(@Param("locName") String locName);

}
