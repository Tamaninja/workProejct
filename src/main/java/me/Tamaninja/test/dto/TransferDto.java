package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferDto implements Serializable {
    private Long id;
    private String identifier;
    private InventoryDto origin;
    private InventoryDto destination;
    private Date transferTimestamp;
    private List<PalletDto> pallets;

    public TransferDto() {
    }

    public TransferDto(Long id, String identifier, InventoryDto origin, InventoryDto destination, Date transferTimestamp, List<PalletDto> pallets) {
        this.id = id;
        this.identifier = identifier;
        this.origin = origin;
        this.destination = destination;
        this.transferTimestamp = transferTimestamp;
        this.pallets = pallets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public InventoryDto getOrigin() {
        return origin;
    }

    public void setOrigin(InventoryDto origin) {
        this.origin = origin;
    }

    public InventoryDto getDestination() {
        return destination;
    }

    public void setDestination(InventoryDto destination) {
        this.destination = destination;
    }

    public Date getTransferTimestamp() {
        return transferTimestamp;
    }

    public void setTransferTimestamp(Date transferTimestamp) {
        this.transferTimestamp = transferTimestamp;
    }

    public List<PalletDto> getPallets() {
        return pallets;
    }

    public void setPallets(List<PalletDto> pallets) {
        this.pallets = pallets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDto entity = (TransferDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.identifier, entity.identifier) &&
                Objects.equals(this.origin, entity.origin) &&
                Objects.equals(this.destination, entity.destination) &&
                Objects.equals(this.transferTimestamp, entity.transferTimestamp) &&
                Objects.equals(this.pallets, entity.pallets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier, origin, destination, transferTimestamp, pallets);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "identifier = " + identifier + ", " +
                "origin = " + origin + ", " +
                "destination = " + destination + ", " +
                "transferTimestamp = " + transferTimestamp + ", " +
                "pallets = " + pallets + ")";
    }
}
