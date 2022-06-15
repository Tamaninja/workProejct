package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "pallets")
public class Pallet {
    @Id
    @Column(nullable = false,name = "pallet_barcode",unique = true)
    private Long barcode;
    @Column(nullable = false,name = "pallet_weight_gross")
    private double weightGross;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pallet_container_name", referencedColumnName = "container_name")
    private PalletContainer palletContainer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pallet_type_name",referencedColumnName = "pallet_type_name")
    private PalletType palletType;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "pallet_content", referencedColumnName = "pallet_content")
    private PalletContent palletContent;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "pallet_location", referencedColumnName = "location_id")
    private Location palletLocation;


    @Column(nullable = true, name = "pallet_container_amount")
    private int amount;

    @Column(nullable = true, name = "pallet_weight_net")
    private double weightNet;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    public Pallet(Long barcode, double weight, PalletContainer palletContainer, PalletType palletType, PalletContent palletContent, Location palletLocation, int amount) {
        this.barcode = barcode;
        this.weightGross = weight;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.palletContent = palletContent;
        this.palletLocation = palletLocation;
        this.amount = amount;
        this.weightNet = (weightGross - (palletContainer.getWeight() * amount) - palletType.getWeight());
    }

    public Pallet(){

    }

    public Long getBarcode() {
        return this.barcode;
    }

    @Override
    public String toString() {
        return "Pallet{" +
                "barcode=" + barcode +
                ", weightGross=" + weightGross +
                ", palletContainer=" + palletContainer +
                ", palletType=" + palletType +
                ", palletContent=" + palletContent +
                '}';
    }
}
