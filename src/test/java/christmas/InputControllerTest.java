package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InputControllerTest {
    @Test
    void 메뉴_입력을_WootecoMenu객체로(){
        InputController inputController = new InputController();
        assertThat(new WootecoMenu("티본스테이크", 1)).isEqualToComparingFieldByField(inputController.inputToWootechMenu("티본스테이크-1"));
    }
//    @Test
//    void 메뉴_형식이_예시와_다른_경우(){
//        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new InputContoller.checkHyhpen("티본스테이크1")).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
//    }
}
