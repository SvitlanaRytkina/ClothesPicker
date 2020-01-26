package models.clothes.top.jumper;

import models.clothes.Season;
import models.clothes.top.TopPartClothes;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Jumper extends TopPartClothes {
    private JumperType type;

    public Jumper() {
    }

    public Jumper(int id, JumperType type, String color, List<Season> season, String comment) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.season = season;
        this.comment = comment;
    }

    public JumperType getType() {
        return type;
    }

    public boolean choose(int temperature) {
        int[] typeTemperatures = getType().getTemperature();
        return typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jumper)) return false;
        Jumper jumper = (Jumper) o;
        return getId() == jumper.getId() && getColor().equals(jumper.getColor()) && getSeason().equals(jumper.getSeason()) &&
                getComment().equals(jumper.getComment()) && getType() == jumper.getType();
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
