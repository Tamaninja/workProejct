package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = false, scale = 2)
    private double weightGross;
    @Column(nullable = false, scale = 2)
    private double weightNet;

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


    @ManyToMany(mappedBy = "pallets")
    private List<Transfer> transfers = new ArrayList<>();


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    private Pool pool;

    public Pallet(Long barcode, PalletType palletType, PalletContainer palletContainer, short containerAmount, PalletContent palletContent, double weightGross, double weightNet, Inventory palletInventory, Pool pool) {
        this.barcode = barcode;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.location = palletInventory;
        this.containerAmount = containerAmount;
        this.pool = pool;
    }

    public Pallet(){

    }

    public void setWeight(double weightGross) {
        this.weightGross = weightGross;
        this.weightNet = weightGross - (palletContainer.getWeight() * containerAmount) - palletType.getWeight();
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Long getBarcode() {
        return barcode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getWeightGross() {
        return weightGross;
    }

    public double getWeightNet() {
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
        this.location = inventory;
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
