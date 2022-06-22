package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "palletType")

public class PalletType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "palletType_id", nullable = false, unique = true)
    private Integer id;
    @Column(name = "palletType_name", nullable = false, unique = true)
    private String name;
    @Column(name = "palletType_weight", nullable = false)
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
