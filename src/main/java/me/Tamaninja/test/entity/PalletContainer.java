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
    @Column(nullable = false, scale = 2)
    private double weight;

    @Column(nullable = false)
    private Short defaultAmount;

    public PalletContainer(String name, double weight, Short defaultAmount) {
        this.name = name;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }
    public PalletContainer() {

    }
    public String getName() {
        return (name);
    }
    public double weight() {
        return (weight);
    }
    public Short defaultAmount() {
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
