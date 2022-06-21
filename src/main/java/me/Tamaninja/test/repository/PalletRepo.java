package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PalletRepo extends JpaRepository<Pallet,Long> {


    @Query(value = "SELECT max(p.id) FROM pallet p")
    Long maxId();

    @Query(value = "SELECT sum(p.palletWeightGross), sum(p.palletWeightNet), sum(p.palletAmount) FROM pallet p WHERE p.transfer = :transfer_id")
    String totalGrossWeightByTransfer(@Param("transfer_id") Transfer transfer);
}
