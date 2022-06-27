package me.Tamaninja.test.enums;

public enum ItemTypes {
    PALLET_TYPE("-palletType-"),
    PALLET_TRAY("-palletTray-"),
    PALLET_CONTENT("-palletContent-");
    private final String type;

    ItemTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (this.type);
    }
}
