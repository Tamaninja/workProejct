package me.Tamaninja.test.repository;

import me.Tamaninja.test.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepo extends JpaRepository<Truck, Integer> {

}
