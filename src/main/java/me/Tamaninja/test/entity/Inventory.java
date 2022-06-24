package me.Tamaninja.test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "inventory")
public class Inventory implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private Integer id;
    private String description;


    @OneToMany(mappedBy = "inventory")
    private List<Pallet> pallets;

    @OneToMany(mappedBy = "origin")
    private List<Transfer> sent;

    @OneToMany(mappedBy = "destination")
    private List<Transfer> received;



    public Inventory(Integer inventoryId, String description) {
        this.id = inventoryId;
        this.description = description;
    }

    public Inventory() {}

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", pallets=" + pallets +
                ", sent=" + sent +
                ", received=" + received +
                '}';
    }
}
