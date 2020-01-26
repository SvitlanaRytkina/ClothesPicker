package models.clothes.bottom.skirt;

import models.clothes.Season;
import models.clothes.bottom.BottomPartClothes;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Skirt extends BottomPartClothes {
    private SkirtLength length;
    private SkirtType type;
    private boolean isWornWithTights;

    public Skirt() {
    }

    public Skirt(int id, SkirtType type, SkirtLength length, String color, boolean isWornWithTights, List<Season> season, String comment) {
        this.id = id;
        this.type = type;
        this.length = length;
        this.color = color;
        this.isWornWithTights = isWornWithTights;
        this.season = season;
        this.comment = comment;
    }

    public boolean choose(int temperature) {
        int[] typeTemperatures = getLength().getTemperature();
        return typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1];
    }

    public SkirtLength getLength() {
        return length;
    }

    public SkirtType getType() {
        return type;
    }

    public void setType(SkirtType type) {
        this.type = type;
    }

    public boolean isWornWithTights() {
        return isWornWithTights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skirt)) return false;
        Skirt skirt = (Skirt) o;
        return super.getId() == skirt.getId() && super.getColor().equals(skirt.getColor()) && super.getComment().equals(skirt.getComment()) &&
                super.getSeason().equals(skirt.getSeason()) && isWornWithTights() == skirt.isWornWithTights() &&
                getLength() == skirt.getLength() && getType() == skirt.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getComment(), getSeason(), getLength(), getType(), isWornWithTights());
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s %s skirt worn with%s tights [%s] for %s", id, color, length.getLength(), type.getType(),
                isWornWithTights ? " " : "out", comment, getStringSeasons(season));
    }
}
