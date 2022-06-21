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
    @Column(nullable = false,name = "pallet_weight_gross")
    private double palletWeightGross;

    @Column(name = "pallet_weight_net")
    private double palletWeightNet;


    @ManyToOne
    @JoinColumn(name = "pallet_container")
    private PalletContainer palletContainer;


    @Column(name = "pallet_container_amount")
    private Integer amount;


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

    @ManyToOne
    @JoinColumn(name = "pallet_transfer_id")
    private Transfer transfer;

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public Pallet(Long palletBarcode, PalletType palletType, PalletContainer palletContainer, Integer amount, PalletContent palletContent, double weight, Inventory palletInventory) {
        this.palletBarcode = palletBarcode;
        this.palletWeightGross = weight;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.palletInventory = palletInventory;
        this.amount = amount;
        this.palletWeightNet = (palletWeightGross - (palletContainer.getWeight() * amount) - palletType.getWeight());
    }

    public Pallet(){

    }

    public double getPalletWeightGross() {
        return palletWeightGross;
    }

    public double getPalletWeightNet() {
        return palletWeightNet;
    }

    @Override
    public String toString() {
        return "Pallet{" +
                "palletBarcode=" + palletBarcode +
                ", palletWeightGross=" + palletWeightGross +
                ", palletWeightNet=" + palletWeightNet +
                ", palletContainer=" + palletContainer +
                ", amount=" + amount +
                ", palletType=" + palletType +
                ", palletTimestamp=" + palletTimestamp +
                ", palletInventory=" + palletInventory +
                ", palletContent=" + palletContent +
                '}';
    }
}
