package me.Tamaninja.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @Column(nullable = false)
    private Integer defaultAmount;

    public PalletContainer(String name, double weight, Integer defaultAmount) {
        this.name = name;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }
    public PalletContainer() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getDefaultAmount() {
        return defaultAmount;
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
