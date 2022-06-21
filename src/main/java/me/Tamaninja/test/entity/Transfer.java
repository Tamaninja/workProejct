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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pallet_transfer_id")
    private List<Pallet> pallets = new ArrayList<>();

    @Column(nullable = false, updatable = false, name = "transfer_timestamp")
    @CreationTimestamp
    private Date transferTimestamp;


    public Transfer(Long transferId, Inventory transferFrom, Inventory transferUsing, Inventory transferTo) {
        this.transferId = transferId;
        this.transferFrom = transferFrom;
        this.transferUsing = transferUsing;
        this.transferTo = transferTo;
    }
    public Transfer() {

    }

    public void refresh() {
        weight_gross = 0;
        weight_net = 0;
        for (Pallet pallet:pallets) {
            weight_gross = weight_gross + pallet.getPalletWeightGross();
            weight_net = weight_net + pallet.getPalletWeightNet();
        }

    }


    public void addToDelivery(Pallet pallet) {
        pallets.add(pallet);
        this.refresh();
    }


}
