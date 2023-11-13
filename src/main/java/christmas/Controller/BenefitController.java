package christmas.Controller;

import christmas.Domain.WootecoMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitController {
    DiscountController discountController;

    public BenefitController(DiscountController discountController) {
        this.discountController = discountController;
    }


    public Map<String, Integer> getBenefitList(int day, List<WootecoMenu> orderedMenu){
        Map<String, Integer> menus = new HashMap<>();
        PriceController priceController = new PriceController();

        int pay = priceController.totalAmountBeforeDiscount(orderedMenu);
        if (pay < 10_000){
            return menus;
        }

        menus.put("크리스마스 디데이 할인", discountController.getX_masDiscount(day));
        menus.put("평일 할인", discountController.getWeekdayDiscount(day, orderedMenu));
        menus.put("주말 할인", discountController.getWeekendDiscount(day, orderedMenu));
        menus.put("특별 할인", discountController.getSpecialDiscount(day));
        menus.put("증정 이벤트", discountController.getGiftDiscount(priceController.totalAmountBeforeDiscount(orderedMenu)));

        return menus;
    }

    public int getTotalBenefitAmount(int day, List<WootecoMenu> orderedMenu){
        Map<String, Integer> menus = getBenefitList(day, orderedMenu);
        return menus.values().stream().mapToInt(i->i).sum();
    }

    public int getFinalCost(int day, List<WootecoMenu> orderedMenu){
        PriceController priceController = new PriceController();
        int totalAmountBeforeDiscount = priceController.totalAmountBeforeDiscount(orderedMenu);
        if (discountController.getGiftDiscount(totalAmountBeforeDiscount) < 25){
            return totalAmountBeforeDiscount - getTotalBenefitAmount(day, orderedMenu);
        }
        return totalAmountBeforeDiscount - getTotalBenefitAmount(day, orderedMenu) + 25000;

    }

    public String getBadge(int day, List<WootecoMenu> orderedMenu){
        int benefit = getTotalBenefitAmount(day, orderedMenu);
        if (benefit >= 20_000){
            return "산타";
        }
        else if (benefit < 20_000 && benefit >= 10_000){
            return "트리";
        }
        else if (benefit < 10_000 && benefit >= 5_000){
            return "별";
        }
        return "없음";
    }

}
