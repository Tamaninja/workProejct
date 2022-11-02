package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Transfer implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne()
    private Inventory origin;

    @ManyToOne()
    private Inventory destination;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @ManyToMany()
    @JoinTable(name = "transfer_pallets",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "pallets_barcode"))
    private List<Pallet> pallets = new ArrayList<>();


    public Transfer(String id, Inventory origin, Inventory destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }
    public Transfer(Inventory origin, Inventory destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Transfer() {

    }

    public String getId() {
        return id;
    }


    public Inventory getOrigin() {
        return origin;
    }


    public Inventory getDestination() {
        return destination;
    }

    public Date getTransferTimestamp() {
        return transferTimestamp;
    }


    public List<Pallet> getPallets() {
        return pallets;
    }

    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
        pallet.setLocation(destination);
    }


    @Override
    public String toString() {
        return (this.id);
    }
}
