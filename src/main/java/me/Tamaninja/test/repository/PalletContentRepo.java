package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalletContentRepo extends JpaRepository<PalletContent, Integer> {

}