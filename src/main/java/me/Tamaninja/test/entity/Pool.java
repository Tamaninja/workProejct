package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "pool")
public class Pool implements Serializable {





    private String description;
    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private Pool parent;

    @OneToMany(mappedBy = "parent")
    private List<Pool> children = new ArrayList<>();

    @OneToMany(mappedBy = "pool")
    private List<Pallet> pallets = new ArrayList<>();
    @ManyToOne()
    private Inventory associatedInventory;

    @OneToOne(optional = false)
    @JoinColumn(name = "pool_id", nullable = false, unique = true)
    private Inventory poolInventory;

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    public Pool() {
        id = UUID.randomUUID();
        this.poolInventory = new Inventory(this);
    }

    public Inventory getPoolInventory() {
        return poolInventory;
    }

    public void setPoolInventory(Inventory poolInventory) {
        this.poolInventory = poolInventory;
    }

    public Pool(String description) {
        id = UUID.randomUUID();
        this.description = description;
        this.poolInventory = new Inventory(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Inventory getAssociatedInventory() {
        return associatedInventory;
    }

    public void setAssociatedInventory(Inventory associatedInventory) {
        this.associatedInventory = associatedInventory;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
    }

    public List<Pool> getChildren() {
        return children;
    }

    public void setChildren(List<Pool> children) {
        this.children = children;
    }

    public Pool getParent() {
        return parent;
    }

    public void setParent(Pool parent) {
        this.parent = parent;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
