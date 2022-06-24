package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "palletContainer")

public class PalletContainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, precision = 6, scale = 2)
    private float weight;

    @Column(nullable = false)
    private Short defaultAmount;

    public PalletContainer(String name, float weight, Short defaultAmount) {
        this.name = name;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }
    public PalletContainer() {

    }
    public String getName() {
        return (name);
    }
    public double getWeight() {
        return (weight);
    }
    public Short getDefaultAmount() {
        return (defaultAmount);
    }

    @Override
    public String toString() {
        return "PalletContainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", defaultAmount=" + defaultAmount +
                '}';
    }
}
