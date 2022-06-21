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
    @Column(name = "transfer_id", unique = true)
    private Long transferId;

    @ManyToOne()
    @JoinColumn(name = "transfer_from", referencedColumnName = "INVENTORY_ID")
    private Inventory transferFrom;

    @ManyToOne()
    @JoinColumn(name = "transfer_using", referencedColumnName = "INVENTORY_ID")
    private Inventory transferUsing;

    @ManyToOne()
    @JoinColumn(name = "transfer_to", referencedColumnName = "INVENTORY_ID")
    private Inventory transferTo;

    @Column(name = "transfer_weight_gross")
    double weight_gross;

    @Column(name = "transfer_weight_net")
    double weight_net;

    @Column(name = "transfer_amount")
    Integer transferAmount;



    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;

    @OneToMany(mappedBy = "transfer")
    private List<Pallet> pallets = new ArrayList<>();

    public Transfer(Long transferId, Inventory transferFrom, Inventory transferUsing, Inventory transferTo) {
        this.transferId = transferId;
        this.transferFrom = transferFrom;
        this.transferUsing = transferUsing;
        this.transferTo = transferTo;
    }
    public Transfer() {

    }

    public void refresh(double weight_gross, double weight_net, Integer transfer_amount) {
        this.weight_gross = weight_gross;
        this.weight_net = weight_net;
        this.transferAmount = transfer_amount;
    }


    public void addToDelivery(Pallet pallet) {
        pallets.add(pallet);
    }
}
