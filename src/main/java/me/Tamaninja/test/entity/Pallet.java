package me.Tamaninja.test.entity;

import javax.persistence.*;

@Entity(name = "pallets")
public class Pallet {
    @Id
    @Column(nullable = false,name = "pallet_barcode",unique = true)
    private Long barcode;
    @Column(nullable = false,name = "weight_gross")
    private int weightGross;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "container_name", referencedColumnName = "container_name")
    private PalletContainer palletContainer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pallet_type_name",referencedColumnName = "pallet_type_name")
    private  PalletType palletType;

    public Pallet(Long barcode, int weight, PalletContainer palletContainer, PalletType palletType) {
        this.barcode = barcode;
        this.weightGross = weight;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
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
                '}';
    }
}
