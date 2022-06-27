package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalletTypeDto implements Serializable {

    @JsonIgnore
    private Integer id;
    private String name;
    private float weight;

    public PalletTypeDto(Integer id, String name, float weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public PalletTypeDto() {}

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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalletTypeDto entity = (PalletTypeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.weight, entity.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "weight = " + weight + ")";
    }
}
