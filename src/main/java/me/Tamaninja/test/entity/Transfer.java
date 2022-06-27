package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(referencedColumnName = "name")
    private Inventory origin;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "name")

    private Inventory destination;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @ManyToMany()
    @JoinTable(name = "transfer_pallets",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "pallets_barcode"))
    private List<Pallet> pallets = new ArrayList<>();

    public Transfer(Integer id, Inventory origin, Inventory destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }
    public Transfer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Inventory getOrigin() {
        return origin;
    }

    public void setOrigin(Inventory origin) {
        this.origin = origin;
    }

    public Inventory getDestination() {
        return destination;
    }

    public void setDestination(Inventory destination) {
        this.destination = destination;
    }

    public Date getTransferTimestamp() {
        return transferTimestamp;
    }

    public void setTransferTimestamp(Date transferTimestamp) {
        this.transferTimestamp = transferTimestamp;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
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
                ", destination=" + destination +
                ", pallets=" + pallets +
                ", transferTimestamp=" + transferTimestamp +
                '}';
    }
}
