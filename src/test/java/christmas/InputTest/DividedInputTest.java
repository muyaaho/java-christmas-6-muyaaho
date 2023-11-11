package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import christmas.InputController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DividedInputTest {
    InputController inputController;
    @BeforeEach
    void setUp(){
        inputController = new InputController();
    }

    private void assertOneInput(String inputString){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> inputController.inputToWootechMenu(inputString)).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }


    @Test
    void 메뉴입력에_하이픈이_없는_경우(){
        assertOneInput("티본스테이크1");
    }

    @Test
    void 메뉴입력에_숫자가_없는경우(){
        assertOneInput("티본스테이크-");
        assertOneInput("티본스테이크- ");
        assertOneInput("티본스테이크-\n");
    }

    @Test
    void 메뉴_이름이_없는_경우(){
        assertOneInput("-1");
    }

    @Test
    void 메뉴_이름과_숫자_둘다_없는_경우(){
        assertOneInput("-");
        assertOneInput("  -  ");
        assertOneInput("-\n");
    }

    @Test
    void 메뉴입력에_숫자가_들어오지_않는_경우(){
        assertOneInput("티본스테이크-a");
        assertOneInput("티본스테이크-.");
        assertOneInput("티본스테이크-,");
    }

}
