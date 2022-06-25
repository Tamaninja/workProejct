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
    public PalletContent(String name) {
        this.name = name;
    }


    public PalletContent() {}

    public Integer getId() {
        return id;
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
