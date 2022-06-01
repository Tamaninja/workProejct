package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletRepo extends JpaRepository<Pallet,Long> {
    Pallet findById(long id);
    @Query(value = "SELECT max(p.id) FROM pallets p")
    Long maxId();
}
