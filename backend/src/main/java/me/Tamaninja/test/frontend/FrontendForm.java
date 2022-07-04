package me.Tamaninja.test.frontend;

import java.util.ArrayList;
import java.util.List;

public class FrontendForm {
    private String endpoint;
    private List<FrontendField> fields = new ArrayList<>();
    public FrontendForm(String endpoint) {
        this.endpoint = endpoint;
    }
    public void addField(FrontendField field) {
        this.fields.add(field);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public List<FrontendField> getFields() {
        return fields;
    }
}
