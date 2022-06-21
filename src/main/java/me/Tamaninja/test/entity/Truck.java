package me.Tamaninja.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "truck")
public class Truck {
    @Id
    @Column(nullable = false,name = "truck_id",unique = true)
    private Long truckId;
    @Column(name = "truck_driver")
    private String driverName;

    public Truck(Long truckId, String driverName) {
        this.truckId = truckId;
        this.driverName = driverName;
    }

    public Truck(){}
}
