package me.Tamaninja.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "transfers")
public class Transfer implements Serializable {

    @Id
    @Column(name = "delivery_id", nullable = false, unique = true)
    private Long deliveryId;

    public Transfer(Long deliveryId) {
        this.deliveryId = deliveryId;
    }
    public Transfer() {

    }

}
