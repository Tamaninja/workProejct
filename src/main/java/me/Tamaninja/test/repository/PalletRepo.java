package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletRepo extends JpaRepository<Pallet,Long> {


    @Query(value = "SELECT max(p.id) FROM pallet p")
    Long maxId();
}
