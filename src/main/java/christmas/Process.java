package christmas;

import christmas.Controller.Input.Validate;
import christmas.Domain.GenerateBenefitStatus;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.OrderedItemsController;
import christmas.Domain.Record.BenefitStatus;
import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.List;

public class Process {

    InputView inputView;
    OutputView outputView;
    Validate validate;
    public Process(InputView inputView, OutputView outputView, Validate validate){
        this.inputView = inputView;
        this.outputView = outputView;
        this.validate = validate;
    }

    public void run(){
        // TODO: 비슷한 이름 바꾸기
        OrderStatus orderStatus = getOrder();
        BenefitStatus benefitStatus = new GenerateBenefitStatus(orderStatus, new DiscountController()).generate();
        printResult(orderStatus, benefitStatus);
    }

    private void printResult(OrderStatus orderStatus, BenefitStatus benefitStatus){
        outputView.printPreview(orderStatus);
        outputView.printOrderedMenu(orderStatus);
        outputView.printBeforeDiscount(orderStatus);
        outputView.printGift(orderStatus);
        // TODO: beneiftList도 1000원 이하라면 0원이라서 outputController 수정 가능 여기 가격 안받아도 될 듯
        outputView.printBenefitList(benefitStatus);
        outputView.printTotalBeneift(benefitStatus);
        outputView.printFinalCost(benefitStatus);
        outputView.printBadge(benefitStatus);
    }

    private OrderStatus getOrder(){
        int day = getDay(new DayController());
        List<WootecoMenu> orderedMenu = getFoods(new OrderedItemsController(validate));
        return new GenerateOrderStatus(day, orderedMenu).generate();
    }

    private int getDay(DayController dayController){
        try{
            inputView.printAskDay();
            return dayController.getDay(inputView.getInput());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getDay(new DayController());
        }
    }

    // TODO: 이름 OrderedItemsController
    private List<WootecoMenu> getFoods(OrderedItemsController orderedItemsController){
        try{
            inputView.printAskMenu();
            return orderedItemsController.setOrderedMenu(inputView.getInput());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getFoods(new OrderedItemsController(validate));
        }
    }
}
