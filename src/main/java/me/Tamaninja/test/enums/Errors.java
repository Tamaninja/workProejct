package me.Tamaninja.test.enums;

public enum Errors {
    ALREADY_EXISTS ("ERROR: ALREADY EXISTS"),
    PALLET_CONTAINER_NOT_FOUND("ERROR: PALLET CONTAINER NOT FOUND"),
    PALLET_TYPE_NOT_FOUND("ERROR: PALLET TYPE NOT FOUND"),
    PALLET_CONTENTS_NOT_FOUND("ERROR: PALLET TYPE NOT FOUND");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (this.message);
    }
}