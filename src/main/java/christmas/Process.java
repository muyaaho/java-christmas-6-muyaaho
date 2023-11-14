package christmas;

import christmas.Controller.BenefitController;
import christmas.Controller.DiscountController;
import christmas.Controller.Input.DayController;
import christmas.Controller.Input.InputController;
import christmas.Controller.Input.OrderedItemsController;
import christmas.Controller.PriceController;
import christmas.Domain.WootecoMenu;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.ArrayList;
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
        DayController dayController = new DayController("1");
        inputView.introduce();
        boolean success = false;
        // TODO: 꼭 함수로 쪼개서 아래 dayController는 return 받아 쓰도록 하기
        while (!success) {
            try{
                inputView.printAskDay();
                dayController = new DayController(inputView.getInput());
                success = true;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        List<WootecoMenu> orderedMenu = new ArrayList<>();
        success = false;
        while (!success){
            try{
                inputView.printAskMenu();
                orderedMenu = inputController.setOrderedMenu(new OrderedItemsController(), inputView.getInput());
                success = true;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        inputView.printComment(dayController);
        outputView.printOrderedMenu();
        orderedMenu.stream().forEach(s -> outputView.printString(s.toString()));

        int beforeBenefit = priceController.totalAmountBeforeDiscount(orderedMenu);
        outputView.printBeforeDiscount(beforeBenefit);
        outputView.printGift(priceController.canGift(beforeBenefit));

        Map<String, Integer> benefitList = benefitController.getBenefitList(dayController.getDay(), orderedMenu);
        // TODO: 할인 전, 총 혜택, 할인 후 갖고 있는 객체 만들기
        int totalBenefit = benefitController.getTotalBenefitAmount(dayController.getDay(), orderedMenu);
        outputView.printBenefitList(totalBenefit, benefitList);

        outputView.printTotalBeneift(benefitController.getTotalBenefitAmount(dayController.getDay(), orderedMenu));

        outputView.printFinalCost(benefitController.getFinalCost(dayController.getDay(), orderedMenu));

        // TODO: orderedMenu, day를 갖고 있는 현재 상태? 객체 만들자..
        outputView.printBadge(benefitController.getBadge(dayController.getDay(), orderedMenu));


    }

}
