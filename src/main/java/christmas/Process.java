package christmas;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.InputController;
import christmas.Controller.Input.OrderedItemsController;
import christmas.Domain.BenefitStatus;
import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.OrderStatus;
import christmas.Domain.WootecoMenu;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.List;
import java.util.Map;

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
        BenefitStatus benefitStatus = getBenefitStatus(orderStatus, new BenefitController(new DiscountController()));
        inputView.printComment(orderStatus.day());
        orderedMenu(orderStatus);
        beforeTotalAndGift(orderStatus);
        usedBenefitController(new BenefitController(new DiscountController()), orderStatus, benefitStatus);
    }

    private OrderStatus getOrder(DayController dayController, InputController inputController){

        int day = getDay(dayController);
        List<WootecoMenu> orderedMenu = getFoods(inputController);
        GenerateOrderStatus generateOrderStatus = new GenerateOrderStatus(day, orderedMenu);
        return generateOrderStatus.generate();
    }

    private BenefitStatus getBenefitStatus(OrderStatus orderStatus, BenefitController benefitController){
        return new BenefitStatus(benefitController.getTotalBenefitAmount(orderStatus), benefitController.getFinalCost(
                orderStatus));
    }

    // TODO: 이거 아닌 것 같음 한 객체에서 몇가지 일이나 하는거임?
    private void usedBenefitController(BenefitController benefitController, OrderStatus orderStatus, BenefitStatus benefitStatus){
        Map<String, Integer> benefitList = benefitController.getBenefitList(orderStatus);
        // TODO: beneiftList도 1000원 이하라면 0원이라서 outputController 수정 가능 여기 가격 안받아도 될 듯
        outputView.printBenefitList(benefitStatus.totalBenefitCost(), benefitList);
        outputView.printTotalBeneift(benefitStatus.totalBenefitCost());
        outputView.printFinalCost(benefitStatus.finalCost());
        outputView.printBadge(benefitController.getBadge(orderStatus));
    }


    // TODO 한 가지 일로 쪼개야 할 것 같음
    private void beforeTotalAndGift(OrderStatus orderStatus){
        int beforeBenefit = orderStatus.totalCost();
        outputView.printBeforeDiscount(beforeBenefit);
        outputView.printGift(orderStatus.canGift());
    }

    private void orderedMenu(OrderStatus orderStatus){
        outputView.printOrderedMenu();
        orderStatus.foods().stream().forEach(s -> outputView.printString(s.toString()));
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
