package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepo extends JpaRepository<Transfer, Long> {

}
