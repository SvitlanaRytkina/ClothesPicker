package models.clothes.top.shirt;

public enum ShirtType {
    GYPSY("Gypsy") {
        @Override
        public int[] getTemperature() {
            return new int[] {0, 40};
        }
    },
    BLOUSE("Blouse") {
        @Override
        public int[] getTemperature() {
            return new int[] {-10, 40};
        }
    },
    SHIRT("Shirt") {
        @Override
        public int[] getTemperature() {
            return new int[] {-30, 30};
        }
    },
    SHORT_SLEEVE_SHORT("Short sleeve shirt") {
        @Override
        public int[] getTemperature() {
            return new int[] {10, 40};
        }
    };

    private String type;

    ShirtType(String type) {
        this.type = type;
    }

    public abstract int[] getTemperature();

    public static ShirtType getByValue(String value) {
        for (ShirtType type : ShirtType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Shirt type with value %s wasn't found.", value));
    }

    public String getType() {
        return type;
    }
}
