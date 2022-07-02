package me.Tamaninja.test.frontend;

import java.util.ArrayList;
import java.util.List;
public class FrontendCard {
    private String key;
    private Object value;
    private List<Object> options;
    public FrontendCard(String key, Object value) {
        this.key = key;
        this.value = value;
        this.options = new ArrayList<>();
        options.add(this);
    }
    public FrontendCard(String key, Object value, List<Object> options) {
        this.key = key;
        this.value = value;
        this.options = options;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public List<Object> getOptions() {
        return options;
    }
}
