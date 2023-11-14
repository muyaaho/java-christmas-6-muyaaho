package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.MenuBoard;
import christmas.Controller.PriceController;
import christmas.Domain.OrderStatus;
import christmas.Domain.WootecoMenu;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceControllerTest {

    PriceController priceController;
    @BeforeEach
    void setUp(){
        priceController = new PriceController();
    }

    private OrderStatus makeInput(List<WootecoMenu> orderItems){
        GenerateOrderStatus generateOrderStatus = new GenerateOrderStatus(0, orderItems);
        return generateOrderStatus.generate();
    }

    @Test
    void 할인_전_총주문_금액(){
        List<WootecoMenu> input = Arrays.asList(new WootecoMenu("타파스", 1), new WootecoMenu("제로콜라", 1));
        assertEquals(makeInput(input).totalCost(), 8500);
        List<WootecoMenu> input2 = Arrays.asList(new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1));
        assertEquals(makeInput(input2).totalCost(), 142000);
    }

    @Test
    void enum_check(){
        assertEquals(MenuBoard.getPrice("제로콜라"), 3000);
    }

    //TODO: test 객체 어디 하나에 만들자..이 안되겠다
    @Test
    void 증정이벤트_샴페인_1개(){
        List<WootecoMenu> input1 = Arrays.asList(new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1));
        assertTrue(makeInput(input1).canGift());
        List<WootecoMenu> input2 = Arrays.asList(new WootecoMenu("타파스", 1), new WootecoMenu("제로콜라", 1));
        assertFalse(makeInput(input2).canGift());
    }
}
