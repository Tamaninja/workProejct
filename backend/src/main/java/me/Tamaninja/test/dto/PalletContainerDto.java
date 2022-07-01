package me.Tamaninja.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalletContainerDto implements Serializable {
    private String identifier;

    public PalletContainerDto() {
    }

    public PalletContainerDto(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalletContainerDto entity = (PalletContainerDto) o;
        return Objects.equals(this.identifier, entity.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return (this.identifier);
    }
}
