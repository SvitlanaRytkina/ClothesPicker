package models.clothes.top.shirt;

import models.clothes.Season;
import models.clothes.top.TopPartClothes;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Shirt extends TopPartClothes {
    private ShirtType type;
    private Material material;

    public Shirt() {
    }

    public Shirt(int id, ShirtType type, Material material, String color, List<Season> season, String comment) {
        super(id, color, season, comment);
        this.type = type;
        this.material = material;
    }

    public ShirtType getType() {
        return type;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean choose(int temperature) {
        int[] typeTemperatures = getType().getTemperature();
        int[] materialTemperatures = getMaterial().getTemperature();
        return typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1] &&
                materialTemperatures[0] <= temperature && temperature <= materialTemperatures[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shirt)) return false;
        Shirt shirt = (Shirt) o;
        return getId() == shirt.getId() && getType() == shirt.getType() &&
                getMaterial() == shirt.getMaterial() && getColor().equals(shirt.getColor()) &&
                getSeason().equals(shirt.getSeason()) && getComment().equals(shirt.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getMaterial(), getColor(), getSeason(), getComment());
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s %s [%s] for %s", getId(), getColor(), material.getMaterial(), type.getType(),
                getComment(), getStringSeasons(getSeason()));
    }
}
