package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;



@Entity(name = "transfer")
public class Transfer implements Serializable {

    @Id
    @Column(name = "transfer_id", unique = true)
    private Long transferId;

    @ManyToOne()
    @JoinColumn(name = "transfer_from", referencedColumnName = "INVENTORY_ID")
    private Inventory transferFrom;

    @ManyToOne()
    @JoinColumn(name = "transfer_truck_id", referencedColumnName = "truck_id")
    private Truck transferTruck;

    @ManyToOne()
    @JoinColumn(name = "transfer_to", referencedColumnName = "INVENTORY_ID")
    private Inventory transferTo;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @ManyToMany
    @JoinTable(name = "transfer_pallets",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "pallet_barcode"))
    private Collection<Pallet> pallets = new ArrayList<>();



    public Transfer(Long transferId, Inventory transferFrom, Truck transferTruck, Inventory transferTo) {
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
}
