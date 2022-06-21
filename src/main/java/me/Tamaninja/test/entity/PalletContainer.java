package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "palletContainer")

public class PalletContainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "palletContainer_id", nullable = false, unique = true)
    private Long id;
    @Column(name = "palletContainer_name", nullable = false, unique = true)
    private String name;
    @Column(name = "palletContainer_weight", nullable = false)
    private double weight;

    @Column(name = "palletContainer_amount", nullable = false)
    private int defaultAmount;

    public PalletContainer(String name, double weight, int defaultAmount) {
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
    public int getDefaultAmount() {
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
