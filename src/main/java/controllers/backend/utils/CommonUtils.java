package controllers.backend.utils;

import models.clothes.Clothes;
import models.clothes.Season;
import models.clothes.bottom.pants.Pants;
import models.clothes.bottom.skirt.Skirt;
import models.clothes.dress.Dress;
import models.clothes.top.jumper.Jumper;
import models.clothes.top.outerwear.Outerwear;
import models.clothes.top.shirt.Shirt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constants.*;
import static models.clothes.Season.getSeasonForMonth;

public class CommonUtils {
    public static List<Season> getSeasons(String value) {
        String[] values = value.split(", ");
        if (!("".equals(values[0]))) {
            return Arrays.stream(values).map(Season::getByValue).collect(Collectors.toList());
        } else {
            return Arrays.asList(Season.values());
        }
    }

    public static String getStringSeasons(List<Season> season) {
        return season.stream().map(Season::getSeason).collect(Collectors.joining(", "));
    }

    public static Season getCurrentSeason() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return getSeasonForMonth(month);
    }

    public static <T extends Clothes> String handleType(T type) {
        if (type instanceof Pants) {
            return PANTS;
        } else if (type instanceof Skirt) {
            return SKIRT;
        } else if (type instanceof Dress) {
            return DRESS;
        } else if (type instanceof Shirt) {
            return SHIRT;
        } else if (type instanceof Jumper) {
            return JUMPER;
        } else if (type instanceof Outerwear) {
            return OUTERWEAR;
        } else {
            return "";
        }
    }

    public static Clothes handleType(String type) {
        switch (type) {
            case PANTS:
                return new Pants();
            case SKIRT:
                return new Skirt();
            case DRESS:
                return new Dress();
            case SHIRT:
                return new Shirt();
            case JUMPER:
                return new Jumper();
            case OUTERWEAR:
                return new Outerwear();
        }
        return null;
    }
}
