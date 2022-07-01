package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PalletContent implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String identifier;
    private Double weightModifier;
    public PalletContent(String identifier, Double weightModifier) {
        this.identifier = identifier;
        this.weightModifier = weightModifier;
    }


    public PalletContent() {}

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Double getWeightModifier() {
        return weightModifier;
    }

    public void setWeightModifier(double weightModifier) {
        this.weightModifier = weightModifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return (this.identifier);
    }
}
