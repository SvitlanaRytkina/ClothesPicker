package models.clothes.top.shirt;

public enum Material {
    COTTON("Cotton") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 30};
        }
    },
    LINEN("Linen") {
        @Override
        public int[] getTemperature() {
            return new int[]{15, 40};
        }
    },
    POLYESTER("Polyester") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 20};
        }
    },
    DENIM("Denim") {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, 10};
        }
    };

    private String material;

    Material(String material) {
        this.material = material;
    }

    public static Material getByValue(String value) {
        for (Material material : Material.values()) {
            if (material.getMaterial().equalsIgnoreCase(value)) {
                return material;
            }
        }
        throw new IllegalArgumentException(String.format("Material with value %s wasn't found.", value));
    }

    public abstract int[] getTemperature();

    public String getMaterial() {
        return material;
    }
}
