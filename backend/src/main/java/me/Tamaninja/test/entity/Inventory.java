package me.Tamaninja.test.entity;


import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Inventory implements Serializable {






    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Transfer> sent;


    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Transfer> received;


    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    @OrderBy("barcode")
    private List<Pallet> pallets;

    @ManyToOne(fetch = FetchType.LAZY)
    private Inventory parent;


    @OneToMany(mappedBy = "parent")
    private List<Inventory> children;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inventory(String description) {
        this.description = description;
    }

    public Inventory(Inventory parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getParent() {
        return parent;
    }

    public void setParent(Inventory parent) {
        this.parent = parent;
    }

    public List<Inventory> getChildren() {
        return children;
    }

    public void setChildren(List<Inventory> children) {
        this.children = children;
    }


    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
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
