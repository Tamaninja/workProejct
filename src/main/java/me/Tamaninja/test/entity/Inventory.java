package me.Tamaninja.test.entity;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "inventory")
public class Inventory implements Serializable {



    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Transfer> sent;


    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Transfer> received;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Pallet> pallets;


    @OneToOne(mappedBy = "poolInventory")
    private Pool pool;

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Inventory(UUID inventoryId, String name) {
        this.id = inventoryId;
        this.name = name;
    }

    public Inventory(Pool pool) {
        this.pool = pool;
        this.id = pool.getId();
    }

    public Pool getPool() {
        return pool;
    }

    public String getName() {
        return name;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transfer> getSent() {
        return sent;
    }

    public void setSent(List<Transfer> sent) {
        this.sent = sent;
    }

    public List<Transfer> getReceived() {
        return received;
    }

    public void setReceived(List<Transfer> received) {
        this.received = received;
    }

    public Inventory() {}

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Inventory inventory = (Inventory) o;
        return id != null && Objects.equals(id, inventory.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
