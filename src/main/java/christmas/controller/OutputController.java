package christmas.controller;

import christmas.domain.Record.BenefitStatus;
import christmas.domain.Record.OrderStatus;
import christmas.domain.Record.WootecoMenu;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputController {
    private final String NONE = "없음";
    private final String GIFT = "샴페인 1개";
    private final String WON = "원";
    private final String MINUS = "-";

    public String moneyFormat(int price) {
        return String.format("%,d", price) + WON;
    }

    public String benefitFormat(int price) {
        if (isNonZero(price)) {
            return MINUS + moneyFormat(price);
        }
        return NONE;
    }

    private boolean isNonZero(int price) {
        return price > 0;
    }

    public String giftFormat(OrderStatus orderStatus) {
        if (isEligibleForGiftDiscount(orderStatus)) {
            return GIFT;
        }
        return NONE;
    }

    private boolean isEligibleForGiftDiscount(OrderStatus orderStatus) {
        return orderStatus.getGiftDiscount() > 0;
    }

    public String benefitListFormat(BenefitStatus benefitStatus) {
        if (isTotalBenefitPositive(benefitStatus)) {
            return benefitStatus.benefitList().entrySet().stream()
                    .filter(this::isPositive)
                    .map(this::getBenefitToString)
                    .collect(Collectors.joining("\n"));
        }
        return NONE;
    }

    private boolean isTotalBenefitPositive(BenefitStatus benefitStatus) {
        return benefitStatus.totalBenefit() > 0;
    }

    private boolean isPositive(Entry<String, Integer> benefit) {
        return benefit.getValue() > 0;
    }

    public String foodListFormat(OrderStatus orderStatus) {
        return orderStatus.foodList().stream()
                .map(WootecoMenu::toString)
                .collect(Collectors.joining("\n"));
    }

    private String getBenefitToString(Entry<String, Integer> benefit) {
        return benefit.getKey() + ": " + MINUS + moneyFormat(benefit.getValue());
    }

}
