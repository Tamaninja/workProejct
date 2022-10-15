package me.Tamaninja.test.frontend;



import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
public class FrontendField<T>{

    private T value;
    private String jsonKey;

    private List<T> possibleValues;

    public FrontendField(String key, T defaultValue) {
        this.jsonKey = key;
        this.value = defaultValue;
    }
    public FrontendField(String key, T defaultValue, List<T> possibleValues) {
        this.jsonKey = key;
        this.value = defaultValue;
        this.possibleValues = possibleValues;
    }

    public T getValue() {
        return value;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public List<T> getPossibleValues() {
        return possibleValues;
    }
}
