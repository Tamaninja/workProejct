package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentDto implements Serializable {
    private Long id;
    private PalletContentDto palletContent;
    private PalletContainerDto palletContainer;
    private PalletDto parent;
    private Integer containerAmount;
    private double weightGross;
    private double weightNet;
    private InventoryDto origin;
    private Date timestamp;

    public ContentDto() {
    }

    public ContentDto(Long id, PalletContentDto palletContent, PalletContainerDto palletContainer, PalletDto parent, Integer containerAmount, double weightGross, double weightNet, InventoryDto origin, Date timestamp) {
        this.id = id;
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.parent = parent;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.origin = origin;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PalletContentDto getPalletContent() {
        return palletContent;
    }

    public void setPalletContent(PalletContentDto palletContent) {
        this.palletContent = palletContent;
    }

    public PalletContainerDto getPalletContainer() {
        return palletContainer;
    }

    public void setPalletContainer(PalletContainerDto palletContainer) {
        this.palletContainer = palletContainer;
    }

    public PalletDto getParent() {
        return parent;
    }

    public void setParent(PalletDto parent) {
        this.parent = parent;
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

    public InventoryDto getOrigin() {
        return origin;
    }

    public void setOrigin(InventoryDto origin) {
        this.origin = origin;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentDto entity = (ContentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.palletContent, entity.palletContent) &&
                Objects.equals(this.palletContainer, entity.palletContainer) &&
                Objects.equals(this.parent, entity.parent) &&
                Objects.equals(this.containerAmount, entity.containerAmount) &&
                Objects.equals(this.weightGross, entity.weightGross) &&
                Objects.equals(this.weightNet, entity.weightNet) &&
                Objects.equals(this.origin, entity.origin) &&
                Objects.equals(this.timestamp, entity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, palletContent, palletContainer, parent, containerAmount, weightGross, weightNet, origin, timestamp);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "palletContent = " + palletContent + ", " +
                "palletContainer = " + palletContainer + ", " +
                "parent = " + parent + ", " +
                "containerAmount = " + containerAmount + ", " +
                "weightGross = " + weightGross + ", " +
                "weightNet = " + weightNet + ", " +
                "origin = " + origin + ", " +
                "timestamp = " + timestamp + ")";
    }
}
