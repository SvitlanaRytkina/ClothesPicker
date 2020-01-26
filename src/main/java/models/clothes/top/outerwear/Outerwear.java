package models.clothes.top.outerwear;

import models.clothes.Season;
import models.clothes.top.TopPartClothes;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Outerwear extends TopPartClothes {
    private OuterwearType type;

    public Outerwear() {
    }

    public Outerwear(int id, OuterwearType type, String color, List<Season> season, String comment) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.season = season;
        this.comment = comment;
    }

    public OuterwearType getType() {
        return type;
    }

    public boolean choose(int temperature) {
        int[] typeTemperatures = getType().getTemperature();
        return temperature <= 15 && typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outerwear)) return false;
        Outerwear outerwear = (Outerwear) o;
        return getId() == outerwear.getId() && getColor().equals(outerwear.getColor()) && getSeason().equals(outerwear.getSeason()) &&
                getComment().equals(outerwear.getComment()) && getType() == outerwear.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getSeason(), getComment(), getType());
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s [%s] for %s", id, color, type.getType(), comment, getStringSeasons(season));
    }
}
