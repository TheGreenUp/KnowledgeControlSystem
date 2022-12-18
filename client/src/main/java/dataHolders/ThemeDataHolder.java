package dataHolders;

import entity.KnlgThemes;
import lombok.Getter;
import lombok.Setter;

public class ThemeDataHolder {
    @Getter
    @Setter
    private KnlgThemes themes;

    private static final ThemeDataHolder instance = new ThemeDataHolder();

    private ThemeDataHolder() {

    }

    public static ThemeDataHolder getInstance() {
        return instance;
    }
}
