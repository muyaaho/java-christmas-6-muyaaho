package christmas.View;

import christmas.Controller.OutputController;
import christmas.Domain.BenefitStatus;
import christmas.Domain.OrderStatus;

public class OutputView {
    OutputController outputController = new OutputController();

    private void printTopic(String name, String output){
        System.out.println();
        System.out.println("<"+name+">");
        System.out.println(output);
    }

    public void printPreview(OrderStatus orderStatus){
        System.out.println("12월 "+orderStatus.day()+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderedMenu(OrderStatus orderStatus){
        printTopic("주문 메뉴", outputController.menuFormat(orderStatus));

    }

    public void printBeforeDiscount(OrderStatus orderStatus){
        printTopic("할인 전 총주문 금액", outputController.moneyFormat(orderStatus.totalCost()));
    }

    public void printGift(OrderStatus orderStatus){
        printTopic("증정 메뉴", outputController.giftFormat(orderStatus));
    }

    public void printBenefitList(BenefitStatus benefitStatus){
        printTopic("혜택 내역", outputController.benefitListFormat(benefitStatus.totalBenefitCost(), benefitStatus.benefitList()));
    }

    public void printTotalBeneift(BenefitStatus benefitStatus){
        printTopic("총혜택 금액", outputController.benefitFormat(benefitStatus.totalBenefitCost()));
    }

    public void printFinalCost(BenefitStatus benefitStatus){
        printTopic("할인 후 예상 결제 금액", outputController.moneyFormat(benefitStatus.finalCost()));
    }

    public void printBadge(BenefitStatus benefitStatus){
        printTopic("12월 이벤트 배지", benefitStatus.badge());
    }

}
