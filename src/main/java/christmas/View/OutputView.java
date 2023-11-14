package christmas.View;

import christmas.Controller.OutputController;
import java.util.Map;

public class OutputView {
    OutputController outputController = new OutputController();

    public void printOrderedMenu(){
        System.out.println();
        System.out.println("<주문 메뉴>");
    }

    public void printBeforeDiscount(int pay){
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(outputController.moneyFormat(pay));
    }

    // TODO: controller를 안에서 다 하고 여기는 출력만 하던가
    public void printGift(boolean gift){
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(outputController.giftFormat(gift));
    }

    public void printBenefitList(int pay, Map<String, Integer> benefitList){
        System.out.println();
        System.out.println("<혜택 내역>");
        System.out.println(outputController.benefitListFormat(pay, benefitList));
    }

    public void printTotalBeneift(int benefit){
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(outputController.benefitFormat(benefit));
    }

    public void printFinalCost(int pay){
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(outputController.moneyFormat(pay));
    }

    public void printBadge(String badge){
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }

    public void printString(String toString){
        System.out.println(toString);
    }

}
