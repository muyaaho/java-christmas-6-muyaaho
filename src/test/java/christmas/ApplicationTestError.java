package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class ApplicationTestError extends NsTest {

    @Test
    void 음료만_주문(){
        assertSimpleTest(() -> {
            runException("25", "레드와인-2");
            assertThat(output()).contains(
                    "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요"
            );
        });
    }
    @Test
    void 음식_공백(){
        assertSimpleTest(() -> {
            runException("25", "초코케이크-2,  티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "초코케이크  -   2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "    ");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });
    }

    @Test
    void 음식_콤마(){
        assertSimpleTest(() -> {
            runException("25", ",,,,,,,초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "초코케이크-2,티본스테이크-2,,,,,,");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "초코케이크-2,");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", ",,,,");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", ",");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });
    }

    @Test
    void 음식_예외(){
        assertSimpleTest(() -> {
            runException("25", "마라탕-2,피자-2,맥주-1");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("25", "초코케이크-2,맥주-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });
    }

    @Test
    void 날짜_공백(){
        assertSimpleTest(() -> {
            runException("", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("  ", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
            );
        });
    }

    @Test
    void 날짜_예외(){
        assertSimpleTest(() -> {
            runException("100", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("-1", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
            );
        });

        assertSimpleTest(() -> {
            runException("10a", "초코케이크-2,티본스테이크-2");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
            );
        });

    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
