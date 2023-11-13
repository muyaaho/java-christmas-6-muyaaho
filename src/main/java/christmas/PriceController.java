package christmas;

import java.util.List;
import javax.swing.MenuElement;

public class PriceController {
    public int totalAmountBeforeDiscount(List<WootecoMenu> menus){
        // enum의 value의 getName해서.. 같은 이름 찾으면 그 가격을 더함
        int totalAmount = 0;
        for(WootecoMenu menu: menus){
            totalAmount += (MenuBoard.getPrice(menu.name()) * menu.count());
        }

        return totalAmount;
    }
}
