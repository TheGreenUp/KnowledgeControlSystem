package dataHolders;

import entity.Stuff;
import lombok.Getter;
import lombok.Setter;

public class StuffDataHolder {

    @Getter
    @Setter
    private Stuff stuff;

    private static final StuffDataHolder instance = new StuffDataHolder();

    private StuffDataHolder() {

    }

    public static StuffDataHolder getInstance() {
        return instance;
    }
}
