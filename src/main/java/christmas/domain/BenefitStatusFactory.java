package christmas.domain;

import static christmas.domain.Badge.*;

import java.util.Collections;
import java.util.Map;

public class BenefitStatusFactory {

    private static final String CHRISTMAS_BENEFIT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_BENEFIT = "평일 할인";
    private static final String WEEKEND_BENEFIT = "주말 할인";
    private static final String SPECIAL_BENEFIT = "특별 할인";
    private static final String GIFT_BENEFIT = "증정 이벤트";
    private static final int MINIMUM_TOTAL_PAY = 10_000;
    private static final int GIFT_BENEFIT_COST = 25_000;
    private final OrderStatus orderStatus;

    public BenefitStatusFactory(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BenefitStatus generate(){
        Map<String, Integer> benefitList = getBenefitList(orderStatus);
        int totalBenefit = getTotalBenefit(orderStatus);
        int finalPrice = getFinalCost(orderStatus);
        Badge badge = getBadge(orderStatus);
        return new BenefitStatus(benefitList, totalBenefit, finalPrice, badge.getBadge());
    }


    private Map<String, Integer> getBenefitList(OrderStatus orderStatus){
        if (orderStatus.totalPrice() < MINIMUM_TOTAL_PAY){
            return Collections.emptyMap();
        }
        return Map.of(
                CHRISTMAS_BENEFIT, orderStatus.getX_masDiscount(),
                WEEKDAY_BENEFIT, orderStatus.getWeekdayDiscount(),
                WEEKEND_BENEFIT, orderStatus.getWeekendDiscount(),
                SPECIAL_BENEFIT, orderStatus.getSpecialDiscount(),
                GIFT_BENEFIT, orderStatus.getGiftDiscount()
                );
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

    private Badge getBadge(OrderStatus orderStatus){
        int benefit = getTotalBenefit(orderStatus);
        if (SANTA.canGetBadge(benefit)){
            return SANTA;
        }
        if (TREE.canGetBadge(benefit)){
            return TREE;
        }
        if (STAR.canGetBadge(benefit)){
            return STAR;
        }
        return NONE;
    }

}
