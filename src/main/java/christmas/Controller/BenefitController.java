package christmas.Controller;

import christmas.Domain.OrderStatus;
import java.util.HashMap;
import java.util.Map;

public class BenefitController {
    DiscountController discountController;

    public BenefitController(DiscountController discountController) {
        this.discountController = discountController;
    }


    public Map<String, Integer> getBenefitList(OrderStatus orderStatus){
        Map<String, Integer> menus = new HashMap<>();

        int pay = orderStatus.totalCost();
        if (pay < 10_000){
            return menus;
        }

        menus.put("크리스마스 디데이 할인", discountController.getX_masDiscount(orderStatus.day()));
        menus.put("평일 할인", discountController.getWeekdayDiscount(orderStatus));
        menus.put("주말 할인", discountController.getWeekendDiscount(orderStatus));
        menus.put("특별 할인", discountController.getSpecialDiscount(orderStatus.day()));
        menus.put("증정 이벤트", discountController.getGiftDiscount(orderStatus));

        return menus;
    }

    public int getTotalBenefitAmount(OrderStatus orderStatus){
        Map<String, Integer> menus = getBenefitList(orderStatus);
        return menus.values().stream().mapToInt(i->i).sum();
    }

    public int getFinalCost(OrderStatus orderStatus){
        int totalAmountBeforeDiscount = orderStatus.totalCost();
        if (discountController.getGiftDiscount(orderStatus) < 25){
            return totalAmountBeforeDiscount - getTotalBenefitAmount(orderStatus);
        }
        return totalAmountBeforeDiscount - getTotalBenefitAmount(orderStatus) + 25000;

    }

    public String getBadge(OrderStatus orderStatus){
        int benefit = getTotalBenefitAmount(orderStatus);
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
