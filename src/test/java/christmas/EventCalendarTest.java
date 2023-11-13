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
    void 평일에_평일할인가격(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("초코케이크", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(3, orderedItems), 2023);

        List<WootecoMenu> orderedItems2 = Arrays.asList(
                new WootecoMenu("초코케이크", 1),
                new WootecoMenu("아이스크림", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(3, orderedItems2), 4046);
    }

    @Test
    void 주말인데평일가격나오는지(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("초코케이크", 1));
        assertEquals(eventCalendar.getWeekdayDiscount(1, orderedItems), 0);
    }

    @Test
    void 주말에_주말할인가격(){
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
    void 평일에_주말할인되는지(){
        List<WootecoMenu> orderedItems = List.of(new WootecoMenu("티본스테이크", 1));
        assertEquals(eventCalendar.getWeekendDiscount(4, orderedItems), 0);
    }


}
