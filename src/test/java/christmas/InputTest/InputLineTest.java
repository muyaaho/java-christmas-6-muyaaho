package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.InputController;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputLineTest {

    InputController inputController;
    @BeforeEach
    void setUp(){
        inputController = new InputController();
    }
    private void assertMakeObj(String inputLine){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> inputController.setOrderedMenu(inputController.seperateComma(inputLine)));
    }
    @Test
    void 콤마를기준으로_나눈값_확인(){
        assertEquals(Arrays.asList("티본스테이크-1","바비큐립-1","초코케이크-2","제로콜라-1"), inputController.seperateComma("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"));
    }

    @Test
    void 콤마_사이에_빈칸이_들어간_경우2(){
        assertMakeObj(",,,,티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertMakeObj(",,,,티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,,,,");
        assertMakeObj("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,,,,");
        assertMakeObj(",,,,티본스테이크-1,바비큐립-1,초코케이크-2,,,,제로콜라-1");
        assertMakeObj(",티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertMakeObj("티본스테이크-1,바비큐립-1,초코케이크-2,    ,제로콜라-1");
    }

    @Test
    void 한개_요소_사이에_공백이_들어간_경우(){
        assertMakeObj("티본스테이크 - 1");
    }

    @Test
    void 올바른_입력_사이에_하이픈이_없는_경우(){
        assertMakeObj("티본스테이크1,티본스테이크-1,초코케이크-2,제로콜라-1");
    }

    @Test
    void 올바른_입력_사이에_숫자가_없는_경우(){
        assertMakeObj("티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-");
    }

    @Test
    void 올바른_입력_사이_숫자가_아닌_경우(){
        assertMakeObj("티본스테이크-1,티본스테이크-1,초코케이크-aa,제로콜라-1");
    }

    @Test
    void 올바른_입력_사이_메뉴_이름이_없는_경우(){
        assertMakeObj("-1,티본스테이크-1,초코케이크-2,제로콜라-1");
    }

    @Test
    void 올바른_입력_사이_이름과_숫자가_없는_경우(){
        assertMakeObj("-,티본스테이크-1,초코케이크-2,제로콜라-1");
    }

    @Test
    void 모든_요소가_겹치는_경우(){
        assertMakeObj("-,-  1,초코케이크-a,제로콜라-1");
    }

    @Test
    void 중복된_메뉴가_입력되는_경우() {
        assertMakeObj("티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-1");
    }

}
