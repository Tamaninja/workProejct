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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String identifier;

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



    public Transfer(String identifier, Inventory origin, Inventory destination) {
        this.identifier = identifier;
        this.origin = origin;
        this.destination = destination;
    }
    public Transfer(Inventory origin, Inventory destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Transfer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        pallet.setLocation(destination);
    }


    @Override
    public String toString() {
        return (this.identifier);
    }
}
