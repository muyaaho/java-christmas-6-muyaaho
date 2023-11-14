package christmas;

import christmas.Domain.GenerateBenefitStatus;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.InputController;
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

    public Process(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){

        // TODO: 비슷한 이름 바꾸기
        OrderStatus orderStatus = getOrder(new DayController(), new InputController());
        // TODO: 생성의 생성 맞음?
        BenefitStatus benefitStatus = new GenerateBenefitStatus(orderStatus, new DiscountController()).generate();
        outputView.printPreview(orderStatus);
        outputView.printOrderedMenu(orderStatus);   // 주문 메뉴
        outputView.printBeforeDiscount(orderStatus);    // 할인 전 총주문금액
        outputView.printGift(orderStatus);    // 증정 메뉴
        // TODO: beneiftList도 1000원 이하라면 0원이라서 outputController 수정 가능 여기 가격 안받아도 될 듯
        outputView.printBenefitList(benefitStatus);
        outputView.printTotalBeneift(benefitStatus);
        outputView.printFinalCost(benefitStatus);
        outputView.printBadge(benefitStatus);

    }

    private OrderStatus getOrder(DayController dayController, InputController inputController){
        int day = getDay(dayController);
        List<WootecoMenu> orderedMenu = getFoods(inputController);
        GenerateOrderStatus generateOrderStatus = new GenerateOrderStatus(day, orderedMenu);
        return generateOrderStatus.generate();
    }


    private int getDay(DayController dayController){
        try{
            inputView.printAskDay();
            int day = dayController.getDay(inputView.getInput());
            return day;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getDay(new DayController());
        }
    }

    private List<WootecoMenu> getFoods(InputController inputController){
        try{
            inputView.printAskMenu();
            return inputController.setOrderedMenu(new OrderedItemsController(), inputView.getInput());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return getFoods(new InputController());
        }
    }
}
