package christmas;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.InputController;
import christmas.Controller.Input.OrderedItemsController;
import christmas.Controller.PriceController;
import christmas.Domain.OrderStatus;
import christmas.Domain.WootecoMenu;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.List;
import java.util.Map;
import org.mockito.internal.matchers.Or;

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
        inputView.printComment(orderStatus.day());
        orderedMenu(orderStatus);
        beforeTotalAndGift(new PriceController(), orderStatus);
        usedBenefitController(new BenefitController(new DiscountController()), orderStatus);
    }

    // TODO: 이거 아닌 것 같음 한 객체에서 몇가지 일이나 하는거임?
    private void usedBenefitController(BenefitController benefitController, OrderStatus orderStatus){
        Map<String, Integer> benefitList = benefitController.getBenefitList(orderStatus.day(), orderStatus.foods());
        // TODO: 할인 전, 총 혜택, 할인 후 갖고 있는 객체 만들기
        int totalBenefit = benefitController.getTotalBenefitAmount(orderStatus.day(), orderStatus.foods());
        outputView.printBenefitList(totalBenefit, benefitList);
        outputView.printTotalBeneift(benefitController.getTotalBenefitAmount(orderStatus.day(), orderStatus.foods()));
        outputView.printFinalCost(benefitController.getFinalCost(orderStatus.day(), orderStatus.foods()));
        // TODO: orderedMenu, day를 갖고 있는 현재 상태? 객체 만들자..
        outputView.printBadge(benefitController.getBadge(orderStatus.day(), orderStatus.foods()));
    }


    // TODO 한 가지 일로 쪼개야 할 것 같음
    private void beforeTotalAndGift(PriceController priceController, OrderStatus orderStatus){
        int beforeBenefit = priceController.totalAmountBeforeDiscount(orderStatus.foods());
        outputView.printBeforeDiscount(beforeBenefit);
        outputView.printGift(priceController.canGift(beforeBenefit));
    }

    private void orderedMenu(OrderStatus orderStatus){
        outputView.printOrderedMenu();
        orderStatus.foods().stream().forEach(s -> outputView.printString(s.toString()));
    }

    private OrderStatus getOrder(DayController dayController, InputController inputController){
        int day = getDay(dayController);
        List<WootecoMenu> orderedMenu = getFoods(inputController);
        return new OrderStatus(day, orderedMenu);
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
