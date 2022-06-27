package me.Tamaninja.test.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContent palletContent;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "name")
    private PalletContainer palletContainer;

    @ManyToOne(optional = false)
    private Pallet parent;

    private Integer containerAmount;

    @Column(nullable = false, scale = 2)
    private double weightGross;
    @Column(nullable = false, scale = 2)
    private double weightNet;

    @ManyToOne()
    private Inventory origin;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    public Content() {
    }

    public Content(Pallet parent, PalletContent palletContent, PalletContainer palletContainer, Integer containerAmount, double weightGross, Inventory origin) {
        this.parent = parent;
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.weightNet = weightGross - (containerAmount * palletContainer.getWeight());
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PalletContent getPalletContent() {
        return palletContent;
    }

    public void setPalletContent(PalletContent palletContent) {
        this.palletContent = palletContent;
    }

    public PalletContainer getPalletContainer() {
        return palletContainer;
    }

    public void setPalletContainer(PalletContainer palletContainer) {
        this.palletContainer = palletContainer;
    }

    public Integer getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(Integer containerAmount) {
        this.containerAmount = containerAmount;
    }

    public double getWeightGross() {
        return weightGross;
    }

    public void setWeightGross(double weightGross) {
        this.weightGross = weightGross;
    }

    public double getWeightNet() {
        return weightNet;
    }

    public void setWeightNet(double weightNet) {
        this.weightNet = weightNet;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
