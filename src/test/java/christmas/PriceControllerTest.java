package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @Test
    void 할인_전_총주문_금액(){
        List<WootecoMenu> input = Arrays.asList(new WootecoMenu("타파스", 1), new WootecoMenu("제로콜라", 1));
        assertEquals(priceController.totalAmountBeforeDiscount(input), 8500);
        List<WootecoMenu> input2 = Arrays.asList(new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1));
        assertEquals(priceController.totalAmountBeforeDiscount(input2), 142000);
    }

    @Test
    void enum_check(){
        assertEquals(MenuBoard.getPrice("제로콜라"), 3000);
    }

    
    @Test
    void 증정이벤트_샴페인_1개(){
        assertEquals(priceController.giftEvent(142000), "샴페인 1개");
        assertEquals(priceController.giftEvent(8500), "없음");
    }
}
