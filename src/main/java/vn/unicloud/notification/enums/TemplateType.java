package vn.unicloud.notification.enums;

public enum TemplateType {
    SMS("SMS"), EMAIL("EMAIL");
    private final String value;

    TemplateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
