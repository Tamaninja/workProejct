package me.Tamaninja.test.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PalletContainer implements Serializable {


    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column(nullable = false, scale = 2)
    private double weight;

    @JsonIgnore
    @Column(nullable = false)
    private Integer defaultAmount;

    public PalletContainer(String id, double weight, Integer defaultAmount) {
        this.id = id;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }
    public PalletContainer() {

    }
    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getDefaultAmount() {
        return defaultAmount;
    }

    @Override
    public String toString() {
        return (this.id);
    }
}
