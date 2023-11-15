package christmas.Controller;

import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import java.util.Arrays;
import java.util.List;

public class DiscountController {

    private static final int WEEK_DAYS_COUNT = 7;
    private static final int OVER_CHRISTMAS_EVENT = 26;
    private static final int GIFT_DISCOUNT = 25000;
    private static final int CHRISTMAS_EVENT_START = 1000;
    private static final int CHRISTMAS_EVENT_UNIT = 100;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int WEEKEND_DISCOUNT = 2023;
    private static final int WEEKDAY_DISCOUNT = 2023;
    private static final List<Integer> SPECIAL_DAYS = Arrays.asList(3,10,17,24,25,31);
    private static final List<Integer> WEEKEND_MOD = Arrays.asList(1,2);

    public int getWeekdayDiscount(OrderStatus orderStatus){
        int discountAmount = 0;
        if (!WEEKEND_MOD.contains(orderStatus.day()%WEEK_DAYS_COUNT)){
            discountAmount = orderStatus.foodList().stream().map(this::getDessertDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getWeekendDiscount(OrderStatus orderStatus){
        int discountAmount = 0;
        if (WEEKEND_MOD.contains(orderStatus.day()%WEEK_DAYS_COUNT)){
            discountAmount = orderStatus.foodList().stream().map(this::getMainDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getSpecialDiscount(int day){
        if (isSpecialDay(day)){
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    public int getGiftDiscount(OrderStatus orderStatus){
        if(orderStatus.canGift()){
            return GIFT_DISCOUNT;
        }
        return 0;
    }

    public int getX_masDiscount(int day){
        if (day < OVER_CHRISTMAS_EVENT){
            return CHRISTMAS_EVENT_START + (day-1)*CHRISTMAS_EVENT_UNIT;
        }
        return 0;
    }

    private int getDessertDiscount(WootecoMenu item){
        if (item.category().equals("DESSERT")){
            return WEEKEND_DISCOUNT * item.count();
        }
        return 0;
    }

    private int getMainDiscount(WootecoMenu item){
        if (item.category().equals("MAIN")){
            return WEEKDAY_DISCOUNT*item.count();
        }
        return 0;
    }

    private boolean isSpecialDay(int day){
        return SPECIAL_DAYS.contains(day);
    }
}
