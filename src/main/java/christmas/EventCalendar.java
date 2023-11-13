package christmas;

import java.util.Arrays;
import java.util.List;

public class EventCalendar {

    List<Integer> weekend_mod = Arrays.asList(1,2);
    public int getWeekdayDiscount(int day, List<WootecoMenu> orderedItems){
        int discountAmount = 0;
        if (!weekend_mod.contains(day%7)){
            discountAmount = orderedItems.stream().map(this::getDessertDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getWeekendDiscount(int day, List<WootecoMenu> orderedItems){
        int discountAmount = 0;
        if (weekend_mod.contains(day%7)){
            discountAmount = orderedItems.stream().map(this::getMainDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    private int getDessertDiscount(WootecoMenu item){
        if (MenuBoard.getCategory(item.name()).equals("DESSERT")){
            return 2023 * item.count();
        }
        return 0;
    }

    private int getMainDiscount(WootecoMenu item){
        if (MenuBoard.getCategory(item.name()).equals("MAIN")){
            return 2023*item.count();
        }
        return 0;
    }
}
