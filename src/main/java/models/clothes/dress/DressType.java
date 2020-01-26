package models.clothes.dress;

public enum DressType {
    SHIFT("Shift"),
    A_LINE("A-line"),
    SHEATH("Sheath"),
    BODYCON("Bodycon"),
    SHIRT("Shirt");

    private String type;

    DressType(String type) {
        this.type = type;
    }

    public static DressType getByValue(String value) {
        for (DressType type : DressType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Dress type with value %s wasn't found.", value));
    }

    public String getType() {
        return type;
    }
}
