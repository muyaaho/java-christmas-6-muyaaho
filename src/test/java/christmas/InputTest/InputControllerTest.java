package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.InputController;
import christmas.WootecoMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputControllerTest {
    InputController inputController;
    @BeforeEach
    void setUp(){
        inputController = new InputController();
    }
    @Test
    void 메뉴_입력을_WootecoMenu객체로(){
        assertThat(new WootecoMenu("티본스테이크", 1)).isEqualToComparingFieldByField(inputController.inputToWootechMenu("티본스테이크-1"));
    }


    @Test
    void 우테코메뉴에서_string으로(){
        assertEquals(new WootecoMenu("타파스", 1).toString(), "타파스 1개");
        assertEquals(new WootecoMenu("제로콜라", 1).toString(), "제로콜라 1개");
    }
}