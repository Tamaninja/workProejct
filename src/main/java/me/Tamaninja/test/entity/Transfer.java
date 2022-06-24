package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity(name = "transfer")
public class Transfer implements Serializable {

    // need to actually implement

    @Id
    @Column(unique = true)
    private Integer id;

    @ManyToOne()
    private Inventory origin;

    @ManyToOne()
    private Inventory transferTruck;

    @ManyToOne()
    private Inventory destination;

    @ManyToMany(mappedBy = "transfers")
    private List<Pallet> pallets;


    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    public Transfer(Integer id, Inventory origin, Inventory transferTruck, Inventory destination) {
        this.id = id;
        this.origin = origin;
        this.transferTruck = transferTruck;
        this.destination = destination;
    }
    public Transfer() {

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
