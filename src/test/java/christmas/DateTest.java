package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    void 날짜_객체에_저장(){
        ReservationDate reservationDate = new ReservationDate("4");
        assertEquals(4, reservationDate.getDay());

        ReservationDate reservationDate2 = new ReservationDate("31");
        assertEquals(31, reservationDate2.getDay());
    }

    @Test
    void 날짜는_1_이상_31_이하(){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate("41"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate("0"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate("-1")).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    void 날짜에_문자가_들어올때_예외처리(){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate("a123")).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate("aaaaaa")).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new ReservationDate(".")).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}
