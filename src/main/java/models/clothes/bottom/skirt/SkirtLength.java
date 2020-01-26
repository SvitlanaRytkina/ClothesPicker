package models.clothes.bottom.skirt;

public enum SkirtLength {
    MINI("Mini") {
        @Override
        public int[] getTemperature() {
            return new int[]{0, 40};
        }
    },
    MIDI("Midi") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 30};
        }
    },
    MAXI("Maxi") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 30};
        }
    };

    private String length;

    SkirtLength(String length) {
        this.length = length;
    }

    public static SkirtLength getByValue(String value) {
        for (SkirtLength length : SkirtLength.values()) {
            if (length.getLength().equalsIgnoreCase(value)) {
                return length;
            }
        }
        throw new IllegalArgumentException(String.format("Length with value %s wasn't found.", value));
    }

    public abstract int[] getTemperature();

    public String getLength() {
        return length;
    }
}
