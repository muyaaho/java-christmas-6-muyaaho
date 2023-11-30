package christmas;

import christmas.controller.FoodsController;
import christmas.controller.Validator;
import christmas.domain.BenefitStatusFactory;
import christmas.controller.DayController;
import christmas.domain.BenefitStatus;
import christmas.domain.OrderStatus;
import christmas.domain.WootecoMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Process {
    private final InputView inputView;
    private final OutputView outputView;
    private final Validator validate;
    public Process(InputView inputView, OutputView outputView, Validator validate){
        this.inputView = inputView;
        this.outputView = outputView;
        this.validate = validate;
    }

    public void run(){
        inputView.introduce();
        OrderStatus orderStatus = getOrderStatus();
        BenefitStatus benefitStatus = new BenefitStatusFactory(orderStatus).generate();
        printResult(orderStatus, benefitStatus);
        inputView.closeScanner();
    }

    private void printResult(OrderStatus orderStatus, BenefitStatus benefitStatus){
        outputView.printPreview(orderStatus);
        outputView.printOrderedMenu(orderStatus);
        outputView.printBeforeDiscount(orderStatus);
        outputView.printGift(orderStatus);
        outputView.printBenefitList(benefitStatus);
        outputView.printTotalBeneift(benefitStatus);
        outputView.printFinalCost(benefitStatus);
        outputView.printBadge(benefitStatus);
    }

    private OrderStatus getOrderStatus(){
        int day = getDay(new DayController());
        List<WootecoMenu> foods = getFoods(new FoodsController(validate));
        return new OrderStatus(day, foods);
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

    private List<WootecoMenu> getFoods(FoodsController foodsController){
        try{
            inputView.printAskMenu();
            return foodsController.setOrderedMenu(inputView.getInput());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getFoods(new FoodsController(validate));
        }
    }
}
