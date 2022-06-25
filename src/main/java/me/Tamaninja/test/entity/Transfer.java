package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @Column(unique = true)
    private Integer id;

    @ManyToOne()
    private Inventory origin;

    @ManyToOne()
    private Inventory transferTruck;

    @ManyToOne()
    private Inventory destination;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @ManyToMany
    @JoinTable(name = "transfer_pallets",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "pallets_barcode"))
    private List<Pallet> pallets = new ArrayList<>();

    public Transfer(Integer id, Inventory origin, Inventory transferTruck, Inventory destination) {
        this.id = id;
        this.origin = origin;
        this.transferTruck = transferTruck;
        this.destination = destination;
    }
    public Transfer() {

    }



    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
        pallet.setInventory(destination);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", origin=" + origin +
                ", transferTruck=" + transferTruck +
                ", destination=" + destination +
                ", pallets=" + pallets +
                ", transferTimestamp=" + transferTimestamp +
                '}';
    }
}
