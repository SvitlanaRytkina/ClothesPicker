package models.clothes.bottom.pants;

public enum PantsType {
    JEANS("Jeans") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 15};
        }
    },
    CARGO("Cargo") {
        @Override
        public int[] getTemperature() {
            return new int[]{0, 20};
        }
    },
    TROUSERS("Trousers") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 25};
        }
    },
    LEGGINGS("Leggings") {
        @Override
        public int[] getTemperature() {
            return new int[]{10, 40};
        }
    },
    SHORTS("Shorts") {
        @Override
        public int[] getTemperature() {
            return new int[]{20, 40};
        }
    };

    private String type;

    PantsType(String type) {
        this.type = type;
    }

    public static PantsType getByValue(String value) {
        for (PantsType type : PantsType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Pants type with value %s wasn't found.", value));
    }

    public abstract int[] getTemperature();

    public String getType() {
        return type;
    }
}
