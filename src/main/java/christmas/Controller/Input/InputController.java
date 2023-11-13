package christmas.Controller.Input;

import christmas.Domain.WootecoMenu;
import java.util.List;

public class InputController {
    public List<WootecoMenu> setOrderedMenu(OrderedItemsController orderedItemsController, String line){
        List<String> dividedCommaList = orderedItemsController.seperateComma(line);
        return dividedCommaList.stream().map(orderedItemsController::inputToWootechMenu).toList();
    }
}
