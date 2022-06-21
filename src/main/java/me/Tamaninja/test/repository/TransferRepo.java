package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pallet;
import me.Tamaninja.test.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepo extends JpaRepository<Transfer, Long> {
    @Query(value = "SELECT p FROM pallet p WHERE p.transfer = :deliveryId")
    List<Pallet> getPallets(@Param("deliveryId") Transfer transfer);
}
