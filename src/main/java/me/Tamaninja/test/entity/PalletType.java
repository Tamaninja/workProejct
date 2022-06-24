package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "palletType")

public class PalletType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private float weight;
    public PalletType(String name, float weight) {
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
