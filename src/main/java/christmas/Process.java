package christmas;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.InputController;
import christmas.Controller.Input.OrderedItemsController;
import christmas.Controller.PriceController;
import christmas.Domain.WootecoMenu;
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
        // TODO: print별로 쪼갤 수 있음
        InputController inputController = new InputController();
        PriceController priceController = new PriceController();
        BenefitController benefitController = new BenefitController(new DiscountController());
        inputView.introduce();
        inputView.printAskDay();
        DayController dayController = new DayController(inputView.getInput());
        inputView.printAskMenu();
        List<WootecoMenu> orderedMenu = inputController.setOrderedMenu(new OrderedItemsController(), inputView.getInput());
        inputView.printComment(dayController);
        outputView.printOrderedMenu();
        orderedMenu.stream().forEach(s -> outputView.printString(s.toString()));

        int beforeBenefit = priceController.totalAmountBeforeDiscount(orderedMenu);
        outputView.printBeforeDiscount(beforeBenefit);
        outputView.printGift(priceController.giftEvent(beforeBenefit));

        outputView.printBenefitList();
        Map<String, Integer> benefitList = benefitController.getBenefitList(dayController.getDay(), orderedMenu);
        if (benefitController.getTotalBenefitAmount(dayController.getDay(), orderedMenu) == 0){
            outputView.printString("없음");
        }
        // TODO: else 없애기
        else {
            benefitList.entrySet().stream().filter(f -> f.getValue() > 0).forEach(k -> outputView.printString(benefitToString(k.getKey(),
                    k.getValue())));
        }
        
        outputView.printTotalBeneift(benefitController.getTotalBenefitAmount(dayController.getDay(), orderedMenu));

        outputView.printFinalCost(benefitController.getFinalCost(dayController.getDay(), orderedMenu));

        // TODO: orderedMenu, day를 갖고 있는 현재 상태? 객체 만들자..
        outputView.printBadge(benefitController.getBadge(dayController.getDay(), orderedMenu));


    }

    public String benefitToString(String key, int value){
        return key+": "+"-"+String.format("%,d", value)+"원";
    }
}
