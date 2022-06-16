package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "transfer")
public class Transfer {

    @Id
    @Column(name = "delivery_id", nullable = false, unique = true)
    private Long deliveryId;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barcode")
    private List<Pallet> pallets;

    public Transfer(Long deliveryId, List<Pallet> pallets) {
        this.deliveryId = deliveryId;
        this.pallets = pallets;
    }

    public Transfer() {

    }
}
