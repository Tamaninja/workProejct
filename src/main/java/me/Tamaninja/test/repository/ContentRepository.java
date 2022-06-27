package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {

}