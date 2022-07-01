package me.Tamaninja.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PalletContainer implements Serializable {


    @Id
    @Column(nullable = false, unique = true)
    private String identifier;
    @Column(nullable = false, scale = 2)
    private double weight;

    @JsonIgnore
    @Column(nullable = false)
    private Integer defaultAmount;

    public PalletContainer(String identifier, double weight, Integer defaultAmount) {
        this.identifier = identifier;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }
    public PalletContainer() {

    }
    public String getIdentifier() {
        return identifier;
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
                ", identifier='" + identifier + '\'' +
                ", weight=" + weight +
                ", defaultAmount=" + defaultAmount +
                '}';
    }
}
