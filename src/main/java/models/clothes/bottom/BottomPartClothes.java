package models.clothes.bottom;

import models.clothes.Clothes;
import models.clothes.Season;

import java.util.List;

public abstract class BottomPartClothes implements Clothes {
    protected int id;
    protected String color;
    protected List<Season> season;
    protected String comment;

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
}
