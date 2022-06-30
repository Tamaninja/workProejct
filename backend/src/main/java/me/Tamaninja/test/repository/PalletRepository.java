package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalletRepository extends JpaRepository<Pallet,Long> {
    @Query(value = "SELECT COALESCE(MAX(p.barcode), 0)+1 FROM pallet p")
    Long generateBarcode();

//    @Query(value = "SELECT sum(p.palletWeightGross), sum(p.palletWeightNet), sum(p.palletAmount) FROM pallet p WHERE p.transfer = :transfer_id")
//    String totalGrossWeightByTransfer(@Param("transfer_id") Transfer transfer);
}
