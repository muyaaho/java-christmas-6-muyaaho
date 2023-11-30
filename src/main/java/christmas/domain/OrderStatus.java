package christmas.domain;

import static christmas.domain.FoodCategory.*;

import java.util.Arrays;
import java.util.List;

public record OrderStatus(int day, List<WootecoMenu> foods) {
    private static final int OVER_CHRISTMAS_EVENT = 26;
    private static final int CHRISTMAS_EVENT_START = 1000;
    private static final int CHRISTMAS_EVENT_UNIT = 100;
    private static final int GIFT_DISCOUNT = 25000;
    private static final int GIFT_THRESHOLD = 120_000;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int WEEKDAY_DISCOUNT = 2023;
    private static final int WEEK_DAYS_COUNT = 7;
    private static final List<Integer> WEEKEND_MOD = Arrays.asList(1,2);
    private static final List<Integer> SPECIAL_DAYS = Arrays.asList(3,10,17,24,25,31);

    public int getTotalPrice(){
        return foods.stream()
                .mapToInt(this::getMenuPrice)
                .sum();
    }

    private int getMenuPrice(WootecoMenu food) {
        return MenuBoard.getPrice(food.name()) * food.count();
    }

    public int getWeekdayDiscount(){
        int discountAmount = 0;
        if (!WEEKEND_MOD.contains(day%WEEK_DAYS_COUNT)){
            discountAmount = foods.stream().map(this::getDessertDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getWeekendDiscount(){
        int discountAmount = 0;
        if (WEEKEND_MOD.contains(day%WEEK_DAYS_COUNT)){
            discountAmount = foods.stream().map(this::getMainDiscount).mapToInt(i->i).sum();
        }
        return discountAmount;
    }

    public int getX_masDiscount(){
        if (day < OVER_CHRISTMAS_EVENT){
            return CHRISTMAS_EVENT_START + (day-1)*CHRISTMAS_EVENT_UNIT;
        }
        return 0;
    }

    public int getGiftDiscount(){
        if(getTotalPrice() >= GIFT_THRESHOLD){
            return GIFT_DISCOUNT;
        }
        return 0;
    }

    public int getSpecialDiscount(){
        if (isSpecialDay(day)){
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    private int getDessertDiscount(WootecoMenu food){
        if (food.category().equals(DESSERT)){
            return WEEKDAY_DISCOUNT * food.count();
        }
        return 0;
    }

    private int getMainDiscount(WootecoMenu food){
        if (food.category().equals(MAIN)){
            return WEEKDAY_DISCOUNT*food.count();
        }
        return 0;
    }

    private boolean isSpecialDay(int day){
        return SPECIAL_DAYS.contains(day);
    }
}
