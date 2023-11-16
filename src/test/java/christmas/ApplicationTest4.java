package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class ApplicationTest4 extends NsTest {
    @Test
    void 디데이_평일_특별_증정(){
        assertSimpleTest(() -> {
            run("25", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -3,400원",
                    "평일 할인: -4,046원",
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
