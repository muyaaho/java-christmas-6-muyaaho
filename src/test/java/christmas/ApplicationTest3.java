package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class ApplicationTest3 extends NsTest {
    @Test
    void 디데이_평일_특별(){
        assertSimpleTest(() -> {
            run("17", "초코케이크-2,해산물파스타-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -2,600원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 디데이_평일_증정(){
        assertSimpleTest(() -> {
            run("18", "초코케이크-1,티본스테이크-2");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -2,700원",
                    "평일 할인: -2,023원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 디데이_주말_증정(){
        assertSimpleTest(() -> {
            run("22", "초코케이크-1,티본스테이크-2");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -3,100원",
                    "주말 할인: -4,046원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 디데이_특별_증정(){
        assertSimpleTest(() -> {
            run("25", "초코케이크-1,티본스테이크-2");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -3,400원",
                    "평일 할인: -2,023원",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 평일_특별_증정(){
        assertSimpleTest(() -> {
            run("31", "초코케이크-1,티본스테이크-2");
            assertThat(output()).contains(
                    "평일 할인: -2,023원",
                    "증정 이벤트: -25,000원",
                    "특별 할인: -1,000원"
            );
        });
    }



    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
