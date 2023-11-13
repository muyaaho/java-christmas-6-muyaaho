package christmas.Controller.Input;

import christmas.Domain.WootecoMenu;
import java.util.List;

public class InputController {
    public List<WootecoMenu> setOrderedMenu(OrderedItemsController orderedItemsController, List<String> dividedCommaList){
        return dividedCommaList.stream().map(orderedItemsController::inputToWootechMenu).toList();
    }
}
