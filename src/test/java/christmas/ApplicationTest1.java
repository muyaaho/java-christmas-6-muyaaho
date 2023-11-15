package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class ApplicationTest1 extends NsTest {
    @Test
    void 디데이(){
        assertSimpleTest(() -> {
            run("8", "초코케이크-1,레드와인-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -1,700원"
            );
        });
    }

    @Test
    void 평일(){
        assertSimpleTest(() -> {
            run("26", "초코케이크-2");
            assertThat(output()).contains(
                    "평일 할인: -4,046원"
            );
        });
    }

    @Test
    void 주말(){
        assertSimpleTest(() -> {
            run("29", "바비큐립-1");
            assertThat(output()).contains(
                    "주말 할인: -2,023원"
            );
        });
    }


    @Test
    void 특별(){
        assertSimpleTest(() -> {
            run("31", "크리스마스파스타-1");
            assertThat(output()).contains(
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 증정(){
        assertSimpleTest(() -> {
            run("20", "레드와인-2,타파스-1");
            assertThat(output()).contains(
                    "증정 이벤트: -25,000원"
            );
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
