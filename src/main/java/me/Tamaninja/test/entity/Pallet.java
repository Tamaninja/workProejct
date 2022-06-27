package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

@Entity(name = "pallet")
public class Pallet implements Serializable {
    @Id
    @Column(nullable = false,unique = true)
    private Long barcode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory location;

    @ManyToMany(mappedBy = "pallets", fetch = FetchType.LAZY)
    private List<Transfer> transfers;

    private double weightGross;
    private double weightNet;


    @ManyToOne()
    @JoinColumn(referencedColumnName = "name")
    private PalletType palletType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory origin;


    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Content> contents;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    public Pallet(Long barcode, Inventory origin, PalletType palletType) {
        this.barcode = barcode;
        this.location = origin;
        this.origin = origin;
        this.palletType = palletType;
        this.weightGross = palletType.getWeight();
    }

    public Pallet(){

    }
    public void addWeight(double weightGross, double weightNet) {
        this.weightGross += weightGross;
        this.weightNet += weightNet;
    }


    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Inventory getLocation() {
        return location;
    }

    public void setLocation(Inventory location) {
        this.location = location;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public double getWeightGross() {
        return weightGross;
    }

    public void setWeightGross(double weightGross) {
        this.weightGross = weightGross;
    }

    public double getWeightNet() {
        return weightNet;
    }

    public void setWeightNet(double weightNet) {
        this.weightNet = weightNet;
    }

    public PalletType getPalletType() {
        return palletType;
    }

    public void setPalletType(PalletType palletType) {
        this.palletType = palletType;
    }

    public Inventory getOrigin() {
        return origin;
    }

    public void setOrigin(Inventory origin) {
        this.origin = origin;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
