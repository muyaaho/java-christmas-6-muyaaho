package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Domain.GenerateWootecoMenu;
import christmas.Domain.Record.WootecoMenu;
import java.util.List;

public class InputController {
    public List<WootecoMenu> setOrderedMenu(OrderedItemsController orderedItemsController, String line){
        List<String> dividedCommaList = orderedItemsController.seperateComma(line);
        return dividedCommaList.stream().map(this::inputToWootechMenu).toList();
    }

    public WootecoMenu inputToWootechMenu(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        return new GenerateWootecoMenu(dividedHyphen[0], parseInt(dividedHyphen[1])).generateor();
    }

}
