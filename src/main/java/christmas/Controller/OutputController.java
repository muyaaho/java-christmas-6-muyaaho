package christmas.Controller;

import christmas.Domain.OrderStatus;
import christmas.Domain.WootecoMenu;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputController {
    public String moneyFormat(int pay){
        return String.format("%,d", pay)+"원\n";
    }

    public String benefitFormat(int pay){
        if (pay > 0){
            return "-"+moneyFormat(pay);
        }
        return "없음";
    }

    public String giftFormat(boolean gift){
        if (gift){
            return "샴페인 1개";
        }
        return "없음";
    }

    public String benefitListFormat(int benefit, Map<String, Integer> benefitList){
        if (benefit > 0){
            String join = benefitList.entrySet().stream()
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
