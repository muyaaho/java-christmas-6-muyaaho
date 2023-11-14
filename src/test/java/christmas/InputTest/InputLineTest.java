package christmas.InputTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Controller.Input.OrderedItemsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputLineTest {

    OrderedItemsController orderedItemsController;
    @BeforeEach
    void setUp(){
        orderedItemsController = new OrderedItemsController();
    }
    private void assertMakeObj(String inputLine){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> orderedItemsController.setOrderedMenu(inputLine));
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
    void 메뉴에_없는_음식_입력(){
        assertMakeObj("마라탕-1");
        assertMakeObj("피자-1");
        assertMakeObj("맥주-1");
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
        assertMakeObj("티본스테이크--1");
    }

    @Test
    void 중복된_메뉴가_입력되는_경우() {
        assertMakeObj("티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-1");
    }

    @Test
    void 음식_갯수가_20개_넘는_경우(){
        assertMakeObj("티본스테이크-10,초코케이크-10,제로콜라-5");
    }

    @Test
    void 음식_갯수에_0이_들어온_경우(){
        assertMakeObj("티본스테이크-0");
    }

}
