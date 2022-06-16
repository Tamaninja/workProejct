package me.Tamaninja.test.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "pallet_contents")
public class PalletContent implements Serializable {

    @Id
    @Column(name = "pallet_content", nullable = false, unique = true)
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
