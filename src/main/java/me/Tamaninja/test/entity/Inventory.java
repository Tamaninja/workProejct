package me.Tamaninja.test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "inventory")
public class Inventory implements Serializable {

    @Id
    @Column(name = "inventory_id", nullable = false, unique = true)
    private Long inventoryId;

    @Column(name = "inventory_description")
    private String description;

    public Inventory(Long inventoryId, String description) {
        this.inventoryId = inventoryId;
        this.description = description;
    }

    public Inventory() {}

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", description='" + description + '\'' +
                '}';
    }
}
