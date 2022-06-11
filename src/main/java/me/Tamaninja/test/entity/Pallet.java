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
    @JoinColumn(name = "name", referencedColumnName = "container_name")
    private PalletContainer palletContainer;

    public Pallet(Long barcode, int weight, PalletContainer palletContainer) {
        this.barcode = barcode;
        this.weightGross = weight;
        this.palletContainer = palletContainer;
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
