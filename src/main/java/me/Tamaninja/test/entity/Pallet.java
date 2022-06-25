package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "pallet")
public class Pallet implements Serializable {
    @Id
    @Column(nullable = false,unique = true)
    private Long barcode;
    private short containerAmount;

    @Column(nullable = false,precision = 6,scale = 2)
    private float weightGross;
    @Column(nullable = false,precision = 6,scale = 2)
    private float weightNet;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContent palletContent;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContainer palletContainer;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletType palletType;


    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private Inventory location;


    @ManyToMany(mappedBy = "pallets", fetch = FetchType.LAZY)
    private List<Transfer> transfers = new ArrayList<>();


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    public Pallet(Long barcode, PalletType palletType, PalletContainer palletContainer, short containerAmount, PalletContent palletContent, float weightGross, Inventory palletInventory) {
        this.barcode = barcode;
        this.weightGross = weightGross;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.location = palletInventory;
        this.containerAmount = containerAmount;
        this.weightNet = (float) (weightGross - (palletContainer.weight() * containerAmount) - palletType.getWeight());
    }

    public Pallet(){

    }

    public Long getBarcode() {
        return barcode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public float getWeightGross() {
        return weightGross;
    }

    public float getWeightNet() {
        return weightNet;
    }

    public Inventory getLocation() {
        return location;
    }

    public PalletType getPalletType() {
        return palletType;
    }

    public PalletContainer getPalletContainer() {
        return palletContainer;
    }

    public PalletContent getPalletContent() {
        return palletContent;
    }

    public short getContainerAmount() {
        return containerAmount;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setInventory(Inventory inventory) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Pallet{" +
                "barcode=" + barcode +
                ", containerAmount=" + containerAmount +
                ", palletContainer=" + palletContainer +
                ", palletType=" + palletType +
                ", inventory=" + location +
                ", palletContent=" + palletContent +
                ", weightGross=" + weightGross +
                ", weightNet=" + weightNet +
                ", timestamp=" + timestamp +
                '}';
    }
}
