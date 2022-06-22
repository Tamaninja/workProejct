package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Entity(name = "pallet")
public class Pallet implements Serializable {
    @Id
    @Column(nullable = false,name = "pallet_barcode",unique = true)
    private Long palletBarcode;
    @Column(nullable = false,name = "pallet_weight_gross",precision = 6,scale = 2)
    private float palletWeightGross;

    @Column(name = "pallet_weight_net", nullable = false,precision = 6,scale = 2)
    private float palletWeightNet;


    @ManyToOne
    @JoinColumn(name = "pallet_container")
    private PalletContainer palletContainer;


    @Column(name = "pallet_container_amount")
    private short palletAmount;


    @ManyToOne
    @JoinColumn(name = "pallet_type")
    private PalletType palletType;


    @Column(nullable = false, updatable = false, name = "pallet_timestamp")
    @CreationTimestamp
    private Date palletTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pallet_inventory_id", nullable = false)
    private Inventory palletInventory;

    @ManyToOne
    @JoinColumn(name = "pallet_content")
    private PalletContent palletContent;


    public Pallet(Long palletBarcode, PalletType palletType, PalletContainer palletContainer, short palletAmount, PalletContent palletContent, float weight, Inventory palletInventory) {
        this.palletBarcode = palletBarcode;
        this.palletWeightGross = (float) weight;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.palletInventory = palletInventory;
        this.palletAmount = palletAmount;
        this.palletWeightNet = (float) (weight - (palletContainer.getWeight() * palletAmount) - palletType.getWeight());
    }

    public Pallet(){

    }


    @Override
    public String toString() {
        return "Pallet{" +
                "palletBarcode=" + palletBarcode +
                ", palletWeightGross=" + palletWeightGross +
                ", palletWeightNet=" + palletWeightNet +
                ", palletContainer=" + palletContainer +
                ", palletAmount=" + palletAmount +
                ", palletType=" + palletType +
                ", palletTimestamp=" + palletTimestamp +
                ", palletInventory=" + palletInventory +
                ", palletContent=" + palletContent +
                '}';
    }
}
