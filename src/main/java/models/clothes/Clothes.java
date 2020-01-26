package models.clothes;

import java.util.List;

public interface Clothes {
    boolean choose(int temperature);

    int getId();

    List<Season> getSeason();
}
