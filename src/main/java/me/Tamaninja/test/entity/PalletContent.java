package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "palletContent")
public class PalletContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "palletContent")
    private List<Pallet> pallet;

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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
