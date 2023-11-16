package christmas.Controller;

import christmas.Domain.Record.BenefitStatus;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import java.util.stream.Collectors;

public class OutputController {
    private static final String NONE = "없음";
    private static final String GIFT = "샴페인 1개";
    private static final String WON = "원";

    public String moneyFormat(int price){
        return String.format("%,d", price)+WON;
    }

    public String benefitFormat(int price){
        if (price > 0){
            return "-"+moneyFormat(price);
        }
        return NONE;
    }

    public String giftFormat(OrderStatus o){
        if (o.getGiftDiscount()>0){
            return GIFT;
        }
        return NONE;
    }

    public String benefitListFormat(BenefitStatus b){
        if (b.totalBenefit() > 0){
            return b.benefitList().entrySet().stream()
                    .filter(f -> f.getValue() > 0)
                    .map(k -> benefitToString(k.getKey(), k.getValue()))
                    .collect(Collectors.joining("\n"));
        }
        return NONE;
    }

    public String foodListFormat(OrderStatus o){
        return o.foodList().stream().map(WootecoMenu::toString).collect(Collectors.joining("\n"));
    }

    private String benefitToString(String key, int value){
        return key+": "+"-"+moneyFormat(value);
    }

}
