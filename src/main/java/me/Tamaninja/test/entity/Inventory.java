package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "inventory")
public class Inventory implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Transfer> sent;


    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Transfer> received;


    public Inventory(Integer inventoryId, String name) {
        this.id = inventoryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Inventory() {}

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
