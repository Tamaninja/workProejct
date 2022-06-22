package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "palletContent")
public class PalletContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "palletContent_id", nullable = false, unique = true)
    private Integer id;



    @Column(name = "palletContent_name", nullable = false, unique = true)
    private String name;

    public PalletContent(String name) {
        this.name = name;
    }


    public PalletContent() {}
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PalletContent{" +
                "name='" + name + '\'' +
                '}';
    }
}
