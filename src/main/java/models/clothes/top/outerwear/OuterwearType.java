package models.clothes.top.outerwear;

public enum OuterwearType {
    CARDIGAN("Cardigan") {
        @Override
        public int[] getTemperature() {
            return new int[] {-30, 20};
        }
    },
    BLAZER("Blazer") {
        @Override
        public int[] getTemperature() {
            return new int[] {-30, 20};
        }
    },
    JACKET("Jacket") {
        @Override
        public int[] getTemperature() {
            return new int[] {5, 20};
        }
    };

    private String type;

    OuterwearType(String type) {
        this.type = type;
    }

    public abstract int[] getTemperature();

    public static OuterwearType getByValue(String value) {
        for (OuterwearType type : OuterwearType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Outerwear type with value %s wasn't found.", value));
    }

    public String getType() {
        return type;
    }
}
