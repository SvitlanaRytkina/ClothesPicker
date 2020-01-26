package models.clothes.bottom.skirt;

public enum SkirtType {
    A_LINE("A-line"),
    ACCORDION("Accordion"),
    TULIP("Tulip"),
    CIRCLE("Circle"),
    PENCIL("Pencil"),
    STRAIGHT("Straight");

    private String type;

    SkirtType(String type) {
        this.type = type;
    }

    public static SkirtType getByValue(String value) {
        for (SkirtType type : SkirtType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Skirt type with value %s wasn't found.", value));
    }

    public String getType() {
        return type;
    }
}
