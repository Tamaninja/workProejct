package me.Tamaninja.test.entity;


import javax.persistence.*;

@Entity(name = "pallet_types")

public class PalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pallet_type_id", nullable = false, unique = true)
    private Long id;
    @Column(name = "pallet_type_name", nullable = false, unique = true)
    private String name;
    @Column(name = "pallet_type_weight", nullable = false)
    private double weight;

    public PalletType(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    public PalletType() {

    }

    public String getName() {
        return (name);
    }
    public double getWeight() {
        return (weight);
    }

    @Override
    public String toString() {
        return "PalletType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
