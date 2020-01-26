package models.clothes.dress;

import models.clothes.Clothes;
import models.clothes.Season;
import models.clothes.bottom.skirt.SkirtLength;

import java.util.List;
import java.util.Objects;

import static controllers.backend.utils.CommonUtils.getStringSeasons;

public class Dress implements Clothes {
    protected int id;
    protected String comment;
    private String color;
    private List<Season> season;
    private SkirtLength length;
    private DressType type;

    public Dress() {
    }

    public Dress(int id, DressType type, SkirtLength length, String color, List<Season> season, String comment) {
        this.id = id;
        this.type = type;
        this.length = length;
        this.color = color;
        this.season = season;
        this.comment = comment;
    }

    public boolean choose(int temperature) {
        int[] typeTemperatures = getLength().getTemperature();
        return typeTemperatures[0] <= temperature && temperature <= typeTemperatures[1];
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public List<Season> getSeason() {
        return season;
    }

    public String getComment() {
        return comment;
    }

    public SkirtLength getLength() {
        return length;
    }

    public DressType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dress)) return false;
        Dress dress = (Dress) o;
        return getId() == dress.getId() &&
                getColor().equals(dress.getColor()) &&
                getSeason().equals(dress.getSeason()) &&
                getComment().equals(dress.getComment()) &&
                getLength() == dress.getLength() &&
                getType() == dress.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColor(), getSeason(), getComment(), getLength(), getType());
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s %s dress [%s] for %s", id, color, length.getLength(), type.getType(), comment, getStringSeasons(season));
    }
}
