package christmas.Controller;

import christmas.Domain.WootecoMenu;
import java.util.Arrays;
import java.util.List;

public class DiscountController {

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

    public int getSpecialDiscount(int day){
        if (isSpecialDay(day)){
            return 1_000;
        }
        return 0;
    }

    public int getGiftDiscount(int pay){
        if(isGift(new PriceController(), pay)){
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


    private boolean isGift(PriceController priceController, int pay){
        return priceController.canGift(pay);
    }

    private int getDessertDiscount(WootecoMenu item){
        if (item.getCategory().equals("DESSERT")){
            return 2023 * item.getCount();
        }
        return 0;
    }

    private int getMainDiscount(WootecoMenu item){
        if (item.getCategory().equals("MAIN")){
            return 2023*item.getCount();
        }
        return 0;
    }

    private boolean isSpecialDay(int day){
        List<Integer> special_days = Arrays.asList(3,10,17,24,25,31);
        return special_days.contains(day);
    }
}
