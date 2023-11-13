package christmas.Controller;

import christmas.Domain.MenuBoard;
import christmas.Domain.WootecoMenu;
import java.util.List;

public class PriceController {
    public int totalAmountBeforeDiscount(List<WootecoMenu> menus){
        int totalAmount = 0;
        for(WootecoMenu menu: menus){
            totalAmount += (MenuBoard.getPrice(menu.getName()) * menu.getCount());
        }

        return totalAmount;
    }

    private boolean canGift(int price){
        return price >= 120_000;
    }

    public String giftEvent(int price){
        if (canGift(price)){
            return "샴페인 1개";
        }
        return "없음";
    }
}
