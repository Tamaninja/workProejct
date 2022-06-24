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
    private short palletAmount;

    @Column(nullable = false,precision = 6,scale = 2)
    private float weightGross;
    @Column(nullable = false,precision = 6,scale = 2)
    private float weightNet;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContainer palletContainer;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletType palletType;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private Inventory inventory;
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContent palletContent;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Transfer> transfers = new ArrayList<>();


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    public Pallet(Long barcode, PalletType palletType, PalletContainer palletContainer, short palletAmount, PalletContent palletContent, float weightGross, Inventory palletInventory) {
        this.barcode = barcode;
        this.weightGross = weightGross;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.inventory = palletInventory;
        this.palletAmount = palletAmount;
        this.weightNet = (float) (weightGross - (palletContainer.getWeight() * palletAmount) - palletType.getWeight());
    }

    public Pallet(){

    }

    public void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    @Override
    public String toString() {
        return "Pallet{" +
                "barcode=" + barcode +
                ", palletAmount=" + palletAmount +
                ", palletContainer=" + palletContainer +
                ", palletType=" + palletType +
                ", inventory=" + inventory +
                ", palletContent=" + palletContent +
                ", weightGross=" + weightGross +
                ", weightNet=" + weightNet +
                ", timestamp=" + timestamp +
                '}';
    }
}
