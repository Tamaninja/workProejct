package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool, Long> {
}