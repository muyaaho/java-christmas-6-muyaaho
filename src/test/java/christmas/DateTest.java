package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @ParameterizedTest
    @ValueSource(strings = {"4", "1", "31"})
    void 날짜는_1_이상_31_이하(String day){
        assertDoesNotThrow(() -> new ReservationDate(day));
    }

    @ParameterizedTest
    @ValueSource(strings = {"41", "0", "-1", "32"})
    void 날짜는_1_이상_31_이하가_아닌_숫자들(String day){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate(day)).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1234", "aaaa", "."})
    void 날짜에_문자가_들어올때_예외처리(String input){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate(input)).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}
