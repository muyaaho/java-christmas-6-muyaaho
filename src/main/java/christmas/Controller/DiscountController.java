package christmas.Controller;

import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import java.util.Arrays;
import java.util.List;

public class DiscountController {
    private final List<Integer> special_days = Arrays.asList(3,10,17,24,25,31);
    private final List<Integer> weekend_mod = Arrays.asList(1,2);
    public int getWeekdayDiscount(OrderStatus orderStatus){
        int discountAmount = 0;
        if (!weekend_mod.contains(orderStatus.day()%7)){
            discountAmount = orderStatus.foods().stream().map(this::getDessertDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getWeekendDiscount(OrderStatus orderStatus){
        int discountAmount = 0;
        if (weekend_mod.contains(orderStatus.day()%7)){
            discountAmount = orderStatus.foods().stream().map(this::getMainDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getSpecialDiscount(int day){
        if (isSpecialDay(day)){
            return 1_000;
        }
        return 0;
    }

    public int getGiftDiscount(OrderStatus orderStatus){
        if(orderStatus.canGift()){
            return 25_000;
        }
        return 0;
    }

    public int getX_masDiscount(int day){
        if (day < 26){
            return 1000 + (day-1)*100;
        }
        return 0;
    }

    private int getDessertDiscount(WootecoMenu item){
        if (item.category().equals("DESSERT")){
            return 2023 * item.count();
        }
        return 0;
    }

    private int getMainDiscount(WootecoMenu item){
        if (item.category().equals("MAIN")){
            return 2023*item.count();
        }
        return 0;
    }

    private boolean isSpecialDay(int day){
        return special_days.contains(day);
    }
}
