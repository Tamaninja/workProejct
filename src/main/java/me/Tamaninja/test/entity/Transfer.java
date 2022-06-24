package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;



@Entity(name = "transfer")
public class Transfer implements Serializable {

    // need to actually implement

    @Id
    @Column(name = "transfer_id", unique = true)
    private Integer transferId;

    @ManyToOne()
    private Inventory transferFrom;

    @ManyToOne()
    private Inventory transferTruck;

    @ManyToOne()
    private Inventory transferTo;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @ManyToMany
    @JoinTable(name = "transfer_pallets",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "pallet_barcode"))
    private Collection<Pallet> pallets = new ArrayList<>();



    public Transfer(Integer transferId, Inventory transferFrom, Inventory transferTruck, Inventory transferTo) {
        this.transferId = transferId;
        this.transferFrom = transferFrom;
        this.transferTruck = transferTruck;
        this.transferTo = transferTo;
    }
    public Transfer() {

    }

    public void addToTransfer(Pallet pallet) {
        pallets.add(pallet);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferFrom=" + transferFrom +
                ", transferTruck=" + transferTruck +
                ", transferTo=" + transferTo +
                ", transferTimestamp=" + transferTimestamp +
                '}';
    }
}
