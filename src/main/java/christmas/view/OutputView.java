package christmas.view;

import static christmas.view.OutputMessage.*;

import christmas.controller.OutputController;
import christmas.domain.BenefitStatus;
import christmas.domain.OrderStatus;

public class OutputView {
    private final OutputController outputController = new OutputController();

    private void printTopic(String name, String output){
        System.out.println();
        System.out.println("<"+name+">");
        System.out.println(output);
    }

    public void printPreview(OrderStatus orderStatus){
        System.out.printf(PREVIEW.getMessage()+"\n", orderStatus.day());
    }

    public void printOrderedMenu(OrderStatus orderStatus){
        printTopic(ORDERED_MENU.getMessage(), outputController.foodListFormat(orderStatus));
    }

    public void printBeforeDiscount(OrderStatus orderStatus){
        printTopic(BEFORE_DISCOUNT.getMessage(), outputController.moneyFormat(orderStatus.getTotalPrice()));
    }

    public void printGift(OrderStatus orderStatus){
        printTopic(GIFT.getMessage(), outputController.giftFormat(orderStatus));
    }

    public void printBenefitList(BenefitStatus benefitStatus){
        printTopic(BENEFIT_LIST.getMessage(), outputController.benefitListFormat(benefitStatus));
    }

    public void printTotalBeneift(BenefitStatus benefitStatus){
        printTopic(TOTAL_BENEFIT.getMessage(), outputController.benefitFormat(benefitStatus.totalBenefit()));
    }

    public void printFinalCost(BenefitStatus benefitStatus){
        printTopic(FINAL_COST.getMessage(), outputController.moneyFormat(benefitStatus.finalPrice()));
    }

    public void printBadge(BenefitStatus benefitStatus){
        printTopic(BADGE.getMessage(), benefitStatus.badge());
    }

}
