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
    private static final int MINIMUM_TOTAL_PAY = 10_000;
    private static final int GIFT_BENEFIT_COST = 25_000;
    private final DiscountController discountController;
    private final OrderStatus orderStatus;

    public GenerateBenefitStatus(OrderStatus orderStatus, DiscountController discountController) {
        this.orderStatus = orderStatus;
        this.discountController = discountController;
    }

    public BenefitStatus generate(){
        Map<String, Integer> benefitList = getBenefitList(orderStatus);
        int totalBenefit = getTotalBenefit(orderStatus);
        int finalPrice = getFinalCost(orderStatus);
        String badge = getBadge(orderStatus);
        return new BenefitStatus(benefitList, totalBenefit, finalPrice, badge);
    }


    private Map<String, Integer> getBenefitList(OrderStatus orderStatus){
        Map<String, Integer> menus = new HashMap<>();

        int price = orderStatus.totalPrice();
        if (price < MINIMUM_TOTAL_PAY){
            return menus;
        }

        menus.put(CHRISTMAS_BENEFIT, discountController.getX_masDiscount(orderStatus.day()));
        menus.put(WEEKDAY_BENEFIT, discountController.getWeekdayDiscount(orderStatus));
        menus.put(WEEKEND_BENEFIT, discountController.getWeekendDiscount(orderStatus));
        menus.put(SPECIAL_BENEFIT, discountController.getSpecialDiscount(orderStatus.day()));
        menus.put(GIFT_BENEFIT, discountController.getGiftDiscount(orderStatus));

        return menus;
    }

    private int getTotalBenefit(OrderStatus orderStatus){
        Map<String, Integer> menus = getBenefitList(orderStatus);
        return menus.values().stream().mapToInt(i->i).sum();
    }

    private int getFinalCost(OrderStatus orderStatus){
        int totalPrice = orderStatus.totalPrice();
        if (discountController.getGiftDiscount(orderStatus) < GIFT_BENEFIT_COST){
            return totalPrice - getTotalBenefit(orderStatus);
        }
        return totalPrice - getTotalBenefit(orderStatus) + GIFT_BENEFIT_COST;

    }

    private String getBadge(OrderStatus orderStatus){
        int benefit = getTotalBenefit(orderStatus);
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
