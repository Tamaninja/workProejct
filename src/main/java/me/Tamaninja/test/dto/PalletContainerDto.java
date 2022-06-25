package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalletContainerDto implements Serializable {
    private Integer id;
    private String name;
    private double weight;
    private Short defaultAmount;

    public PalletContainerDto(Integer id, String name, double weight, Short defaultAmount) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.defaultAmount = defaultAmount;
    }

    public PalletContainerDto(){}

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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Short getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Short defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalletContainerDto entity = (PalletContainerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.weight, entity.weight) &&
                Objects.equals(this.defaultAmount, entity.defaultAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, defaultAmount);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "weight = " + weight + ", " +
                "defaultAmount = " + defaultAmount + ")";
    }
}
