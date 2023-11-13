package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventCalendarTest {

    EventCalendar eventCalendar;

    @BeforeEach
    void setUp(){
        eventCalendar = new EventCalendar();
    }
    @Test
    void 평일_평일할인(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("초코케이크", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(3, orderedItems), 2023);

        List<WootecoMenu> orderedItems2 = Arrays.asList(
                new WootecoMenu("초코케이크", 1),
                new WootecoMenu("아이스크림", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(3, orderedItems2), 4046);
    }

    @Test
    void 주말_평일할인(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("초코케이크", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(1, orderedItems), 0);
    }

    @Test
    void 주말_주말할인(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("티본스테이크", 1));
        assertEquals(eventCalendar.getWeekendDiscount(15, orderedItems), 2023);
        List<WootecoMenu> orderedItems2 = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("레드와인", 1));
        assertEquals(eventCalendar.getWeekendDiscount(15, orderedItems2), 2023);

        List<WootecoMenu> orderedItems3 = List.of(
                new WootecoMenu("티본스테이크", 2),
                new WootecoMenu("레드와인", 1));
        assertEquals(eventCalendar.getWeekendDiscount(15, orderedItems3), 4046);
    }

    @Test
    void 평일_주말할인(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("티본스테이크", 1));
        assertEquals(eventCalendar.getWeekendDiscount(4, orderedItems), 0);
    }

    @Test
    void 특별할인날짜_특별할인(){
        assertEquals(eventCalendar.getSpecialDiscount(3), 1000);
    }

    @Test
    void 특별할인없는날짜_특별할인(){
        assertEquals(eventCalendar.getSpecialDiscount(28), 0);
    }

    @Test
    void 증정이벤트(){
        assertEquals(eventCalendar.getGiftDiscount(142_000), 25_000);
        assertEquals(eventCalendar.getGiftDiscount(120_000), 25_000);
        assertEquals(eventCalendar.getGiftDiscount(8_500), 0);
    }


}
