package models.clothes.bottom;

import models.clothes.Clothes;
import models.clothes.Season;

import java.util.List;

public abstract class BottomPartClothes implements Clothes {
    private int id;
    private String color;
    private List<Season> season;
    private String comment;

    public BottomPartClothes() {
    }

    public BottomPartClothes(int id, String color, List<Season> season, String comment) {
        this.id = id;
        this.color = color;
        this.season = season;
        this.comment = comment;
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
}
