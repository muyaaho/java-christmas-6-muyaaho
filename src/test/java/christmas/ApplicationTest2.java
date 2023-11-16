package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class ApplicationTest2 extends NsTest {
    @Test
    void 디데이_평일(){
        assertSimpleTest(() -> {
            run("21", "초코케이크-3,레드와인-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -3,000원",
                    "평일 할인: -6,069원"
            );
        });
    }

    @Test
    void 디데이_주말(){
        assertSimpleTest(() -> {
            run("16", "초코케이크-2,해산물파스타-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -2,500원",
                    "주말 할인: -2,023원"
            );
        });
    }

    @Test
    void 디데이_특별(){
        assertSimpleTest(() -> {
            run("3", "해산물파스타-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -1,200원",
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 디데이_증정(){
        assertSimpleTest(() -> {
            run("12", "티본스테이크-3");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -2,100원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 평일_특별(){
        assertSimpleTest(() -> {
            run("31", "초코케이크-2");
            assertThat(output()).contains(
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 평일_증정(){
        assertSimpleTest(() -> {
            run("27", "초코케이크-1,티본스테이크-2");
            assertThat(output()).contains(
                    "평일 할인: -2,023원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 주말_증정(){
        assertSimpleTest(() -> {
            run("15", "티본스테이크-3");
            assertThat(output()).contains(
                    "주말 할인: -6,069원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 특별_증정(){
        assertSimpleTest(() -> {
            run("31", "크리스마스파스타-5");
            assertThat(output()).contains(
                    "특별 할인: -1,000원",
                    "증정 이벤트: -25,000원"
            );
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
