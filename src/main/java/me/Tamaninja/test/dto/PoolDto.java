package me.Tamaninja.test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PoolDto implements Serializable {
    private String description;
    private PoolDto parent;
    private List<PoolDto> children = new ArrayList<>();
    private List<PalletDto> pallets = new ArrayList<>();
    private InventoryDto associatedInventory;
    private InventoryDto poolInventory;
    private UUID id;

    public PoolDto() {
    }

    public PoolDto(String description, PoolDto parent, List<PoolDto> children, List<PalletDto> pallets, InventoryDto associatedInventory, InventoryDto poolInventory, UUID id) {
        this.description = description;
        this.parent = parent;
        this.children = children;
        this.pallets = pallets;
        this.associatedInventory = associatedInventory;
        this.poolInventory = poolInventory;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PoolDto getParent() {
        return parent;
    }

    public void setParent(PoolDto parent) {
        this.parent = parent;
    }

    public List<PoolDto> getChildren() {
        return children;
    }

    public void setChildren(List<PoolDto> children) {
        this.children = children;
    }

    public List<PalletDto> getPallets() {
        return pallets;
    }

    public void setPallets(List<PalletDto> pallets) {
        this.pallets = pallets;
    }

    public InventoryDto getAssociatedInventory() {
        return associatedInventory;
    }

    public void setAssociatedInventory(InventoryDto associatedInventory) {
        this.associatedInventory = associatedInventory;
    }

    public InventoryDto getPoolInventory() {
        return poolInventory;
    }

    public void setPoolInventory(InventoryDto poolInventory) {
        this.poolInventory = poolInventory;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoolDto entity = (PoolDto) o;
        return Objects.equals(this.description, entity.description) &&
                Objects.equals(this.parent, entity.parent) &&
                Objects.equals(this.children, entity.children) &&
                Objects.equals(this.pallets, entity.pallets) &&
                Objects.equals(this.associatedInventory, entity.associatedInventory) &&
                Objects.equals(this.poolInventory, entity.poolInventory) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, parent, children, pallets, associatedInventory, poolInventory, id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "description = " + description + ", " +
                "parent = " + parent + ", " +
                "children = " + children + ", " +
                "pallets = " + pallets + ", " +
                "associatedInventory = " + associatedInventory + ", " +
                "poolInventory = " + poolInventory + ", " +
                "id = " + id + ")";
    }
}
