package vn.unicloud.notification.enums;

public enum SourceEventType {
    DATABASE("DATABASE"),
    RESTAPI("API");
    private final String value;

    SourceEventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
