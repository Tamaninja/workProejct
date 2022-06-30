package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "palletContent")
public class PalletContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
    private Double weightModifier;
    public PalletContent(String name, Double weightModifier) {
        this.name = name;
        this.weightModifier = weightModifier;
    }


    public PalletContent() {}

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeightModifier() {
        return weightModifier;
    }

    public void setWeightModifier(double weightModifier) {
        this.weightModifier = weightModifier;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PalletContent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
