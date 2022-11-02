package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PalletContent implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String id;
    private Double weightModifier;
    public PalletContent(String id, Double weightModifier) {
        this.id = id;
        this.weightModifier = weightModifier;
    }


    public PalletContent() {}

    public Double getWeight() {
        return weightModifier;
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return (this.id);
    }
}
