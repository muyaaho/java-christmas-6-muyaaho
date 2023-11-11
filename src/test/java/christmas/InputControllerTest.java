package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void 메뉴입력에_하이픈이_없는_경우(){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> inputController.inputToWootechMenu("티본스테이크1")).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


}
