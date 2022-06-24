package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PalletTypeRepo extends JpaRepository<PalletType,Integer> {


}
