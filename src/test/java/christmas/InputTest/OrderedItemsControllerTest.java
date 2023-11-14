package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Controller.Input.OrderedItemsController;
import christmas.Domain.GenerateWootecoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderedItemsControllerTest {
    OrderedItemsController orderedItemsController;
    @BeforeEach
    void setUp(){
        orderedItemsController = new OrderedItemsController();
    }

    @Test
    void 우테코메뉴에서_string으로(){
        assertEquals(new GenerateWootecoMenu("타파스", 1).toString(), "타파스 1개");
        assertEquals(new GenerateWootecoMenu("제로콜라", 1).toString(), "제로콜라 1개");
    }
}
