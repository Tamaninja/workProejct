package me.Tamaninja.test.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class InventoryDto implements Serializable {
    private Integer id;
    private String name;
    private List<TransferDto> sent;
    private List<TransferDto> received;

    public InventoryDto(Integer id, String name, List<TransferDto> sent, List<TransferDto> received) {
        this.id = id;
        this.name = name;
        this.sent = sent;
        this.received = received;
    }
    public InventoryDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryDto entity = (InventoryDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.sent, entity.sent) &&
                Objects.equals(this.received, entity.received);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sent, received);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "sent = " + sent + ", " +
                "received = " + received + ")";
    }
}
