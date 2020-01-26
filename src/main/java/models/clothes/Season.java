package models.clothes;

import java.util.Arrays;
import java.util.List;

public enum Season {
    WINTER("Winter", Arrays.asList(12, 1, 2)),
    SPRING("Spring", Arrays.asList(3, 4, 5)),
    SUMMER("Summer", Arrays.asList(6, 7, 8)),
    AUTUMN("Autumn", Arrays.asList(9, 10, 11));

    private String season;
    private List<Integer> seasonMonth;

    Season(String season, List<Integer> seasonMonth) {
        this.season = season;
        this.seasonMonth = seasonMonth;
    }

    public static Season getByValue(String value) {
        for (Season season : Season.values()) {
            if (season.getSeason().equalsIgnoreCase(value)) {
                return season;
            }
        }
        throw new IllegalArgumentException(String.format("Season with value %s wasn't found.", value));
    }

    public static Season getSeasonForMonth(int month) {
        for (Season season : Season.values()) {
            if (season.isInSeason(month))
                return season;
        }
        throw new IllegalArgumentException("Month wasn't found!");
    }

    public boolean isInSeason(int month) {
        return this.seasonMonth.contains(month);  // if months are 0 based, then insert +1 before the )
    }

    public String getSeason() {
        return season;
    }
}
