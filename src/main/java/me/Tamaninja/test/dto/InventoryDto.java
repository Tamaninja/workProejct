package me.Tamaninja.test.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class InventoryDto implements Serializable {
    private String name;
    private List<TransferDto> sent;
    private List<TransferDto> received;
    private List<PalletDto> pallets;
    private PoolDto pool;
    private UUID id;

    public InventoryDto() {
    }

    public InventoryDto(String name, List<TransferDto> sent, List<TransferDto> received, List<PalletDto> pallets, PoolDto pool, UUID id) {
        this.name = name;
        this.sent = sent;
        this.received = received;
        this.pallets = pallets;
        this.pool = pool;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public PoolDto getPool() {
        return pool;
    }

    public void setPool(PoolDto pool) {
        this.pool = pool;
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
        InventoryDto entity = (InventoryDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.sent, entity.sent) &&
                Objects.equals(this.received, entity.received) &&
                Objects.equals(this.pallets, entity.pallets) &&
                Objects.equals(this.pool, entity.pool) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sent, received, pallets, pool, id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "sent = " + sent + ", " +
                "received = " + received + ", " +
                "pallets = " + pallets + ", " +
                "pool = " + pool + ", " +
                "id = " + id + ")";
    }
}
