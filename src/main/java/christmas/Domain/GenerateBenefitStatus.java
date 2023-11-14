package christmas.Domain;

import static christmas.Domain.Enum.Badge.*;

import christmas.Controller.DiscountController;
import christmas.Domain.Record.BenefitStatus;
import christmas.Domain.Record.OrderStatus;
import java.util.HashMap;
import java.util.Map;

public class GenerateBenefitStatus {

    private static final String CHRISTMAS_BENEFIT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_BENEFIT = "평일 할인";
    private static final String WEEKEND_BENEFIT = "주말 할인";
    private static final String SPECIAL_BENEFIT = "특별 할인";
    private static final String GIFT_BENEFIT = "증정 이벤트";
    private DiscountController discountController;
    private OrderStatus orderStatus;

    public GenerateBenefitStatus(OrderStatus orderStatus, DiscountController discountController) {
        this.orderStatus = orderStatus;
        this.discountController = discountController;
    }

    public BenefitStatus generate(){
        Map<String, Integer> benefitList = getBenefitList(orderStatus);
        int totalBenefitCost = getTotalBenefitAmount(orderStatus);
        int finalCost = getFinalCost(orderStatus);
        String badge = getBadge(orderStatus);
        return new BenefitStatus(benefitList, totalBenefitCost, finalCost, badge);
    }


    private Map<String, Integer> getBenefitList(OrderStatus orderStatus){
        Map<String, Integer> menus = new HashMap<>();

        int pay = orderStatus.totalCost();
        if (pay < 10_000){
            return menus;
        }

        menus.put(CHRISTMAS_BENEFIT, discountController.getX_masDiscount(orderStatus.day()));
        menus.put(WEEKDAY_BENEFIT, discountController.getWeekdayDiscount(orderStatus));
        menus.put(WEEKEND_BENEFIT, discountController.getWeekendDiscount(orderStatus));
        menus.put(SPECIAL_BENEFIT, discountController.getSpecialDiscount(orderStatus.day()));
        menus.put(GIFT_BENEFIT, discountController.getGiftDiscount(orderStatus));

        return menus;
    }

    private int getTotalBenefitAmount(OrderStatus orderStatus){
        Map<String, Integer> menus = getBenefitList(orderStatus);
        return menus.values().stream().mapToInt(i->i).sum();
    }

    private int getFinalCost(OrderStatus orderStatus){
        int totalAmountBeforeDiscount = orderStatus.totalCost();
        if (discountController.getGiftDiscount(orderStatus) < 25){
            return totalAmountBeforeDiscount - getTotalBenefitAmount(orderStatus);
        }
        return totalAmountBeforeDiscount - getTotalBenefitAmount(orderStatus) + 25000;

    }

    private String getBadge(OrderStatus orderStatus){
        int benefit = getTotalBenefitAmount(orderStatus);
        if (benefit >= MORE_20000.getPay()){
            return MORE_20000.getBadge();
        }
        else if (benefit >= MORE_10000.getPay()){
            return MORE_10000.getBadge();
        }
        else if (benefit >= MORE_5000.getPay()){
            return MORE_5000.getBadge();
        }
        return NONE.getBadge();
    }

}
