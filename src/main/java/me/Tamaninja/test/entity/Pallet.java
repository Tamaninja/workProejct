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

    private Integer containerAmount;

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


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory location;


    @ManyToMany(mappedBy = "pallets", fetch = FetchType.LAZY)
    private List<Transfer> transfers;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory origin;

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public void setContainerAmount(Integer containerAmount) {
        this.containerAmount = containerAmount;
    }

    public void setWeightGross(double weightGross) {
        this.weightGross = weightGross;
    }

    public void setWeightNet(double weightNet) {
        this.weightNet = weightNet;
    }

    public void setPalletContent(PalletContent palletContent) {
        this.palletContent = palletContent;
    }

    public void setPalletContainer(PalletContainer palletContainer) {
        this.palletContainer = palletContainer;
    }

    public void setPalletType(PalletType palletType) {
        this.palletType = palletType;
    }

    public void setLocation(Inventory location) {
        this.location = location;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public Inventory getOrigin() {
        return origin;
    }

    public void setOrigin(Inventory origin) {
        this.origin = origin;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    public Pallet(Long barcode, PalletType palletType, PalletContainer palletContainer, Integer containerAmount, PalletContent palletContent, double weightGross, double weightNet, Inventory origin) {
        this.barcode = barcode;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.location = origin;
        this.containerAmount = containerAmount;
        this.origin = origin;
    }

    public Pallet(){

    }

    public void setWeight(double weightGross) {
        this.weightGross = weightGross;
        this.weightNet = weightGross - (palletContainer.getWeight() * containerAmount) - palletType.getWeight();
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

    public Integer getContainerAmount() {
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
