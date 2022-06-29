package me.Tamaninja.test.enums;

public enum Errors {
    NOT_FOUND("\twas not found");
    private final String message;

    Errors(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return (this.message);
    }
}
