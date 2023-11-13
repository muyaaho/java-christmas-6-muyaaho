package christmas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record WootecoMenu(String name, int count) {

    public WootecoMenu(String name, int count) {

        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return name +" " + count+"개";
    }
}
