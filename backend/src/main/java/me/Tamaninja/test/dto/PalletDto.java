package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalletDto implements Serializable {

    @JsonIgnore
    private Long id;
    private Long barcode;
    private String location;
    private List<TransferDto> transfers;
    private String palletContent;
    private String palletContainer;
    private Integer containerAmount;
    private double weightGross;
    private double weightNet;
    private String palletType;
    private String origin;
    private Timestamp timestamp;

    public PalletDto() {
    }

    public PalletDto(Long barcode, String palletContent, String palletContainer, Integer containerAmount, double weightGross, String palletType, String origin) {
        this.barcode = barcode;
        this.palletContent = palletContent;
        this.palletContainer = palletContainer;
        this.containerAmount = containerAmount;
        this.weightGross = weightGross;
        this.palletType = palletType;
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TransferDto> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferDto> transfers) {
        this.transfers = transfers;
    }

    public String getPalletContent() {
        return palletContent;
    }

    public void setPalletContent(String palletContent) {
        this.palletContent = palletContent;
    }

    public String getPalletContainer() {
        return palletContainer;
    }

    public void setPalletContainer(String palletContainer) {
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

    public String getPalletType() {
        return palletType;
    }

    public void setPalletType(String palletType) {
        this.palletType = palletType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalletDto entity = (PalletDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.barcode, entity.barcode) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.transfers, entity.transfers) &&
                Objects.equals(this.palletContent, entity.palletContent) &&
                Objects.equals(this.palletContainer, entity.palletContainer) &&
                Objects.equals(this.containerAmount, entity.containerAmount) &&
                Objects.equals(this.weightGross, entity.weightGross) &&
                Objects.equals(this.weightNet, entity.weightNet) &&
                Objects.equals(this.palletType, entity.palletType) &&
                Objects.equals(this.origin, entity.origin) &&
                Objects.equals(this.timestamp, entity.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, location, transfers, palletContent, palletContainer, containerAmount, weightGross, weightNet, palletType, origin, timestamp);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "barcode = " + barcode + ", " +
                "location = " + location + ", " +
                "transfers = " + transfers + ", " +
                "palletContent = " + palletContent + ", " +
                "palletContainer = " + palletContainer + ", " +
                "containerAmount = " + containerAmount + ", " +
                "weightGross = " + weightGross + ", " +
                "weightNet = " + weightNet + ", " +
                "palletType = " + palletType + ", " +
                "origin = " + origin + ", " +
                "timestamp = " + timestamp + ")";
    }
}
