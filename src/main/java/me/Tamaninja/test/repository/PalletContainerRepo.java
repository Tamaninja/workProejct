package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.PalletContainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletContainerRepo extends CrudRepository<PalletContainer,Long> {
}
