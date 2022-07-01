package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDto implements Serializable {
    private String identifier;
    private List<TransferDto> sent;
    private List<TransferDto> received;
    private List<PalletDto> pallets;
    private String parent;
    private List<InventoryDto> children;

    public InventoryDto() {
    }

    public InventoryDto(String name, String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void getIdentifier(String name) {
        this.identifier = identifier;
    }

    public List<TransferDto> getSent() {
        return sent;
    }

    public void setSent(List<TransferDto> sent) {
        this.sent = sent;
    }

    public List<TransferDto> getReceived() {
        return received;
    }

    public void setReceived(List<TransferDto> received) {
        this.received = received;
    }

    public List<PalletDto> getPallets() {
        return pallets;
    }

    public void setPallets(List<PalletDto> pallets) {
        this.pallets = pallets;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<InventoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<InventoryDto> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryDto entity = (InventoryDto) o;
        return Objects.equals(this.identifier, entity.identifier) &&
                Objects.equals(this.sent, entity.sent) &&
                Objects.equals(this.received, entity.received) &&
                Objects.equals(this.pallets, entity.pallets) &&
                Objects.equals(this.parent, entity.parent) &&
                Objects.equals(this.children, entity.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, sent, received, pallets, parent, children);
    }

    @Override
    public String toString() {
        return (this.identifier);
    }
}
