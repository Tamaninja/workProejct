package me.Tamaninja.test.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;


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

    private Timestamp timestamp;
    public Content() {
    }

    public Content(Pallet parent, PalletContent palletContent, PalletContainer palletContainer, Integer containerAmount, double weightGross, double weightNet, Inventory origin) {
        this.parent = parent;
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.origin = origin;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Jerusalem")));
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
