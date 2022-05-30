package me.Tamaninja.test;

import javax.persistence.*;

@Entity(name = "pallets")
public class Pallet {
    @Id
    @Column(nullable = false,name = "pallet_barcode",unique = true)
    private Long barcode;
    @Column(nullable = false,name = "weight_gross")
    private int weightGross;

    public Pallet(Long barcode, int weight) {
        this.barcode = barcode;
        this.weightGross = weight;
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
