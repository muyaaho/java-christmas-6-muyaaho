package christmas.domain;

import static christmas.domain.Badge.*;

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
    private final OrderStatus orderStatus;

    public GenerateBenefitStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
        menus.put(CHRISTMAS_BENEFIT, orderStatus.getX_masDiscount());
        menus.put(WEEKDAY_BENEFIT, orderStatus.getWeekdayDiscount());
        menus.put(WEEKEND_BENEFIT, orderStatus.getWeekendDiscount());
        menus.put(SPECIAL_BENEFIT, orderStatus.getSpecialDiscount());
        menus.put(GIFT_BENEFIT, orderStatus.getGiftDiscount());
        return menus;
    }

    private int getTotalBenefit(OrderStatus orderStatus){
        Map<String, Integer> menus = getBenefitList(orderStatus);
        return menus.values().stream().mapToInt(i->i).sum();
    }

    private int getFinalCost(OrderStatus orderStatus){
        int totalPrice = orderStatus.totalPrice();
        if (orderStatus.getGiftDiscount() < GIFT_BENEFIT_COST){
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
