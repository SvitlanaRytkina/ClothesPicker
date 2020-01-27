package models.clothes.bottom.pants;

import models.clothes.Season;
import models.clothes.bottom.BottomPartClothes;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Pants extends BottomPartClothes {
    private PantsType type;
    private boolean isWornWithBelt;

    public Pants() {
    }

    public Pants(int id, PantsType type, String color, boolean isWornWithBelt, List<Season> season, String comment) {
        super(id, color, season, comment);
        this.type = type;
        this.isWornWithBelt = isWornWithBelt;
    }

    public boolean choose(int temperature) {
        int[] temperatures = getType().getTemperature();
        return temperatures[0] <= temperature && temperature <= temperatures[1];
    }

    public PantsType getType() {
        return type;
    }

    public boolean isWornWithBelt() {
        return isWornWithBelt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pants)) return false;
        Pants pants = (Pants) o;
        return super.getId() == pants.getId() && super.getColor().equals(pants.getColor()) && super.getComment().equals(pants.getComment()) &&
                super.getSeason().equals(pants.getSeason()) && isWornWithBelt() == pants.isWornWithBelt() && getType() == pants.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getComment(), getSeason(), getType(), isWornWithBelt());
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s with%s belt [%s] for %s", getId(), getColor(), type.getType(), isWornWithBelt ? " " : "out",
                getComment(), getStringSeasons(getSeason()));
    }
}
