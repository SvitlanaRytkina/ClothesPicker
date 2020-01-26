package models.clothes.top.jumper;

public enum JumperType {
    SLEEVELESS_SHIRT("Sleeveless shirt") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 40};
        }
    },
    T_SHIRT("T-shirt") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 40};
        }
    },
    JUMPER("Jumper") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 10};
        }
    },
    SWEATSHIRT("Sweatshirt") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 0};
        }
    },
    TURTLENECK_SHIRT("Turtleneck shirt") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 10};
        }
    },
    HOODY("Hoody") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 0};
        }
    };

    private String type;

    JumperType(String type) {
        this.type = type;
    }

    public static JumperType getByValue(String value) {
        for (JumperType type : JumperType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Jumper type with value %s wasn't found.", value));
    }

    public abstract int[] getTemperature();

    public String getType() {
        return type;
    }
}
