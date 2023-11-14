package christmas.Controller;

import christmas.Domain.Record.BenefitStatus;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputController {
    public String moneyFormat(int pay){
        return String.format("%,d", pay)+"원";
    }

    public String benefitFormat(int pay){
        if (pay > 0){
            return "-"+moneyFormat(pay);
        }
        return "없음";
    }

    public String giftFormat(OrderStatus o){
        if (o.canGift()){
            return "샴페인 1개";
        }
        return "없음";
    }

    public String benefitListFormat(BenefitStatus b){
        if (b.totalBenefitCost() > 0){
            String join = b.benefitList().entrySet().stream()
                    .filter(f -> f.getValue() > 0)
                    .map(k -> benefitToString(k.getKey(), k.getValue()))
                    .collect(Collectors.joining("\n"));
            return join;
        }

        return "없음";
    }

    public String menuFormat(OrderStatus orderStatus){
        return orderStatus.foods().stream().map(WootecoMenu::toString).collect(Collectors.joining("\n"));
    }

    private String benefitToString(String key, int value){
        return key+": "+"-"+String.format("%,d", value)+"원";
    }

}
