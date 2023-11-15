package christmas;

import christmas.Controller.Input.FoodsController;
import christmas.Controller.Input.Validate;
import christmas.Domain.GenerateBenefitStatus;
import christmas.Controller.Input.DayController;
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
        inputView.introduce();
        OrderStatus orderStatus = getOrderStatus();
        BenefitStatus benefitStatus = new GenerateBenefitStatus(orderStatus).generate();
        printResult(orderStatus, benefitStatus);
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
        return new GenerateOrderStatus(day, foods).generate();
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
