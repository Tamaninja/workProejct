package me.Tamaninja.test.frontend;

import java.util.ArrayList;
import java.util.List;

public class FrontendField {
    private String jsonKey; //json key
    private Object value;
    private List valueList = new ArrayList<>();

    public FrontendField(String key, Object value) {
        this.jsonKey = key;
        this.value = value;
        this.valueList.add(value);
    }
    public FrontendField(String key, Object value, List options) {
        this.jsonKey = key;
        this.valueList = options;
        this.value = value;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public Object getValue() {
        return value;
    }

    public List<Object> getValueList() {
        return valueList;
    }
}
