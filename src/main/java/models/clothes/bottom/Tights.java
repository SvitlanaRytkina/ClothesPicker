package models.clothes.bottom;

import models.clothes.Clothes;
import models.clothes.Season;

import java.util.List;

public enum Tights implements Clothes {
    DEN20(20) {
        @Override
        public int[] getTemperature() {
            return new int[]{10, 40};
        }
    },
    DEN40(40) {
        @Override
        public int[] getTemperature() {
            return new int[]{5, 9};
        }
    },
    DEN50(50) {
        @Override
        public int[] getTemperature() {
            return new int[]{-5, 4};
        }
    },
    DEN60(60) {
        @Override
        public int[] getTemperature() {
            return new int[]{-10, -6};
        }
    },
    DEN100(100) {
        @Override
        public int[] getTemperature() {
            return new int[]{-30, -11};
        }
    };

    private int id;

    Tights(int id) {
        this.id = id;
    }

    public static Tights getByValue(int value) {
        for (Tights type : Tights.values()) {
            if (type.getId() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Tights with den value %s wasn't found.", value));
    }

    public abstract int[] getTemperature();

    public int getId() {
        return id;
    }

    public List<Season> getSeason() {
        return null;
    }

    @Override
    public boolean choose(int temperature) {
        int[] typeTemperatures = getTemperature();
        return typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1];
    }

    @Override
    public String toString() {
        return String.format("Tights [%s den]", getId());
    }
}
