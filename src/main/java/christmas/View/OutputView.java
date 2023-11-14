package christmas.View;

import christmas.Controller.OutputController;
import christmas.Domain.OrderStatus;
import java.util.Map;

public class OutputView {
    OutputController outputController = new OutputController();

    private void printTopic(String name, String output){
        System.out.println();
        System.out.println("<"+name+">");
        System.out.println(output);
    }

    public void printOrderedMenu(OrderStatus orderStatus){
        printTopic("주문 메뉴", outputController.menuFormat(orderStatus));

    }

    public void printBeforeDiscount(int pay){
        printTopic("할인 전 총주문 금액", outputController.moneyFormat(pay));
    }

    public void printGift(boolean gift){
        printTopic("증정 메뉴", outputController.giftFormat(gift));
    }

    public void printBenefitList(int benefit, Map<String, Integer> benefitList){
        printTopic("혜택 내역", outputController.benefitListFormat(benefit, benefitList));
    }

    public void printTotalBeneift(int benefit){
        printTopic("총혜택 금액", outputController.benefitFormat(benefit));
    }

    public void printFinalCost(int pay){
        printTopic("할인 후 예상 결제 금액", outputController.moneyFormat(pay));
    }

    public void printBadge(String badge){
        printTopic("12월 이벤트 배지", badge);
    }

}
