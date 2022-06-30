package me.Tamaninja.test.dto;

import java.io.Serializable;
import java.util.Objects;

public class PalletContentDto implements Serializable {
    private String identifier;

    public PalletContentDto() {
    }

    public PalletContentDto(String identifier) {
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
        PalletContentDto entity = (PalletContentDto) o;
        return Objects.equals(this.identifier, entity.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "identifier = " + identifier + ")";
    }
}
