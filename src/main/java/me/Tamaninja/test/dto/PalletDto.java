package me.Tamaninja.test.dto;

import me.Tamaninja.test.entity.Pallet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PalletDto implements Serializable {
    private Long barcode;
    private short containerAmount;
    private double weightGross;
    private double weightNet;
    private PalletContentDto palletContent;
    private PalletContainerDto palletContainer;
    private PalletTypeDto palletType;
    private InventoryDto location;
    private List<TransferDto> transfers;
    private Date timestamp;

    public PalletDto(Long barcode, short containerAmount, double weightGross, double weightNet, PalletContentDto palletContent, PalletContainerDto palletContainer, PalletTypeDto palletType, InventoryDto location, List<TransferDto> transfers, Date timestamp) {
        this.barcode = barcode;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.palletType = palletType;
        this.location = location;
        this.transfers = transfers;
        this.timestamp = timestamp;
    }
    public PalletDto() {}

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public short getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(short containerAmount) {
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

    public PalletTypeDto getPalletType() {
        return palletType;
    }

    public void setPalletType(PalletTypeDto palletType) {
        this.palletType = palletType;
    }

    public InventoryDto getLocation() {
        return location;
    }

    public void setLocation(InventoryDto location) {
        this.location = location;
    }

    public List<TransferDto> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferDto> transfers) {
        this.transfers = transfers;
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
        PalletDto entity = (PalletDto) o;
        return Objects.equals(this.barcode, entity.barcode) &&
                Objects.equals(this.containerAmount, entity.containerAmount) &&
                Objects.equals(this.weightGross, entity.weightGross) &&
                Objects.equals(this.weightNet, entity.weightNet) &&
                Objects.equals(this.palletContent, entity.palletContent) &&
                Objects.equals(this.palletContainer, entity.palletContainer) &&
                Objects.equals(this.palletType, entity.palletType) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.transfers, entity.transfers) &&
                Objects.equals(this.timestamp, entity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, containerAmount, weightGross, weightNet, palletContent, palletContainer, palletType, location, transfers, timestamp);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "barcode = " + barcode + ", " +
                "containerAmount = " + containerAmount + ", " +
                "weightGross = " + weightGross + ", " +
                "weightNet = " + weightNet + ", " +
                "palletContent = " + palletContent + ", " +
                "palletContainer = " + palletContainer + ", " +
                "palletType = " + palletType + ", " +
                "location = " + location + ", " +
                "transfers = " + transfers + ", " +
                "timestamp = " + timestamp + ")";
    }
}