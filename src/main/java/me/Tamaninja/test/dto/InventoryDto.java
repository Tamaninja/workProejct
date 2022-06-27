package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDto implements Serializable {
    private String name;
    private Long id;
    private List<TransferDto> sent;
    private List<TransferDto> received;
    private List<PalletDto> pallets;
    private InventoryDto parent;
    private List<InventoryDto> children;

    public InventoryDto() {
    }

    public InventoryDto(String name, Long id, List<TransferDto> sent, List<TransferDto> received, List<PalletDto> pallets, InventoryDto parent, List<InventoryDto> children) {
        this.name = name;
        this.id = id;
        this.sent = sent;
        this.received = received;
        this.pallets = pallets;
        this.parent = parent;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InventoryDto getParent() {
        return parent;
    }

    public void setParent(InventoryDto parent) {
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
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.sent, entity.sent) &&
                Objects.equals(this.received, entity.received) &&
                Objects.equals(this.pallets, entity.pallets) &&
                Objects.equals(this.parent, entity.parent) &&
                Objects.equals(this.children, entity.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, sent, received, pallets, parent, children);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "id = " + id + ", " +
                "sent = " + sent + ", " +
                "received = " + received + ", " +
                "pallets = " + pallets + ", " +
                "parent = " + parent + ", " +
                "children = " + children + ")";
    }
}
