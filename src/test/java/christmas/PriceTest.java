package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.MenuBoard;
import christmas.domain.OrderStatus;
import christmas.domain.WootecoMenuFactory;
import christmas.domain.WootecoMenu;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    void 할인_전_총주문_금액(){
        List<WootecoMenu> input = Arrays.asList(makeMenu("타파스", 1), makeMenu("제로콜라", 1));
        List<WootecoMenu> input2 = Arrays.asList(makeMenu("티본스테이크", 1),
                makeMenu("바비큐립", 1),
                makeMenu("초코케이크", 2),
                makeMenu("제로콜라", 1));

        assertEquals(makeInput(input).getTotalPrice(), 8500);
        assertEquals(makeInput(input2).getTotalPrice(), 142000);
    }

    @Test
    void enum_check(){
        assertEquals(MenuBoard.getPrice("제로콜라"), 3000);
    }

    @Test
    void 증정이벤트_샴페인_1개(){
        List<WootecoMenu> input1 = Arrays.asList(makeMenu("티본스테이크", 1),
                makeMenu("바비큐립", 1),
                makeMenu("초코케이크", 2),
                makeMenu("제로콜라", 1));
        List<WootecoMenu> input2 = Arrays.asList(makeMenu("타파스", 1), makeMenu("제로콜라", 1));

        assertEquals(makeInput(input1).getGiftDiscount(),25_000);
        assertEquals(makeInput(input2).getGiftDiscount(), 0);
    }

    private OrderStatus makeInput(List<WootecoMenu> orderItems){
        return new OrderStatus(0, orderItems);
    }

    private WootecoMenu makeMenu(String name, int count){
        return new WootecoMenuFactory(name, count).generator();
    }
}
