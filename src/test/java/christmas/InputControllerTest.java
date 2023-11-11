package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
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

    private void assertMenuInput(String inputString){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> inputController.inputToWootechMenu(inputString)).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
    @Test
    void 메뉴입력에_하이픈이_없는_경우(){
        assertMenuInput("티본스테이크1");
    }

    // 여기 숫자가 없거나 공백이거나는 날짜 입력받을 때 썼던거 써도 되지 않나? -> 예측하지 말고 일단 반복작업 하자
    // 2보다 작아서 테스트 통과함
    @Test
    void 메뉴입력에_숫자가_없는경우(){
        assertMenuInput("티본스테이크-");
        assertMenuInput("티본스테이크- ");
        assertMenuInput("티본스테이크-\n");
    }
    
    @Test
    void 메뉴_이름이_없는_경우(){
        assertMenuInput("-1");
    }

    @Test
    void 메뉴_이름과_숫자_둘다_없는_경우(){
        assertMenuInput("-");
        assertMenuInput("  -  ");
        assertMenuInput("-\n");
    }

    @Test
    void 메뉴입력에_숫자가_들어오지_않는_경우(){
        assertMenuInput("티본스테이크-a");
        assertMenuInput("티본스테이크-.");
        assertMenuInput("티본스테이크-,");
    }

    @Test
    void 콤마를기준으로_나눈값_확인(){
        assertEquals(Arrays.asList("티본스테이크-1","바비큐립-1","초코케이크-2","제로콜라-1"), inputController.seperateComma("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"));
    }


}
