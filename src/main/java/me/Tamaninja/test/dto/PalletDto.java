package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalletDto implements Serializable {
    private Long barcode;
    private InventoryDto location;
    private List<TransferDto> transfers;
    private double weightGross;
    private double weightNet;
    private PalletTypeDto palletType;
    private InventoryDto origin;
    private List<ContentDto> contents;
    private Date timestamp;

    public PalletDto() {
    }

    public PalletDto(Long barcode, InventoryDto location, List<TransferDto> transfers, double weightGross, double weightNet, PalletTypeDto palletType, InventoryDto origin, List<ContentDto> contents, Date timestamp) {
        this.barcode = barcode;
        this.location = location;
        this.transfers = transfers;
        this.weightGross = weightGross;
        this.weightNet = weightNet;
        this.palletType = palletType;
        this.origin = origin;
        this.contents = contents;
        this.timestamp = timestamp;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
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

    public PalletTypeDto getPalletType() {
        return palletType;
    }

    public void setPalletType(PalletTypeDto palletType) {
        this.palletType = palletType;
    }

    public InventoryDto getOrigin() {
        return origin;
    }

    public void setOrigin(InventoryDto origin) {
        this.origin = origin;
    }

    public List<ContentDto> getContents() {
        return contents;
    }

    public void setContents(List<ContentDto> contents) {
        this.contents = contents;
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
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.transfers, entity.transfers) &&
                Objects.equals(this.weightGross, entity.weightGross) &&
                Objects.equals(this.weightNet, entity.weightNet) &&
                Objects.equals(this.palletType, entity.palletType) &&
                Objects.equals(this.origin, entity.origin) &&
                Objects.equals(this.contents, entity.contents) &&
                Objects.equals(this.timestamp, entity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, location, transfers, weightGross, weightNet, palletType, origin, contents, timestamp);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "barcode = " + barcode + ", " +
                "location = " + location + ", " +
                "transfers = " + transfers + ", " +
                "weightGross = " + weightGross + ", " +
                "weightNet = " + weightNet + ", " +
                "palletType = " + palletType + ", " +
                "origin = " + origin + ", " +
                "contents = " + contents + ", " +
                "timestamp = " + timestamp + ")";
    }
}
