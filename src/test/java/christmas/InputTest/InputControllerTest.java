package christmas.InputTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.WootecoMenu;
import org.junit.jupiter.api.Test;

public class InputControllerTest {
    
    @Test
    void 우테코메뉴에서_string으로(){
        assertEquals(new WootecoMenu("타파스", 1).toString(), "타파스 1개");
        assertEquals(new WootecoMenu("제로콜라", 1).toString(), "제로콜라 1개");
    }
}
