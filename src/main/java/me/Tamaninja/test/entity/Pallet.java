package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity(name = "pallet")
@Table(indexes = {
        @Index(name = "idx_pallet_barcode", columnList = "barcode")
})
public class Pallet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private Long barcode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory location;

    @ManyToMany(mappedBy = "pallets", fetch = FetchType.LAZY)
    private List<Transfer> transfers;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContent palletContent;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContainer palletContainer;

    private Integer containerAmount;

    @Column(nullable = false, scale = 2)
    private double weightGross;
    @Column(nullable = false, scale = 2)
    private double weightNet;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "name")
    private PalletContainer palletType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Inventory origin;

    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;

    public Pallet(Long barcode, Inventory origin, PalletContainer palletType, PalletContent palletContent, PalletContainer palletContainer, Integer containerAmount, double weightGross, double weightNet) {
        this.barcode = barcode;
        this.location = origin;
        this.origin = origin;
        this.palletType = palletType;
        this.palletContainer = palletContainer;
        this.palletContent = palletContent;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Jerusalem")));
    }

    public Pallet(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PalletContent getPalletContent() {
        return palletContent;
    }

    public void setPalletContent(PalletContent palletContent) {
        this.palletContent = palletContent;
    }

    public PalletContainer getPalletContainer() {
        return palletContainer;
    }

    public void setPalletContainer(PalletContainer palletContainer) {
        this.palletContainer = palletContainer;
    }

    public Integer getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(Integer containerAmount) {
        this.containerAmount = containerAmount;
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

    public PalletContainer getPalletType() {
        return palletType;
    }

    public void setPalletType(PalletContainer palletType) {
        this.palletType = palletType;
    }

    public Inventory getOrigin() {
        return origin;
    }

    public void setOrigin(Inventory origin) {
        this.origin = origin;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
