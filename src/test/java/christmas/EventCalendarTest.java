package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(eventCalendar.isSpecialDiscount(3));
    }

    @Test
    void 특별할인없는날짜_특별할인(){
        assertFalse(eventCalendar.isSpecialDiscount(28));
    }

    @Test
    void 증정이벤트(){
        PriceController priceController = new PriceController();
        assertTrue(eventCalendar.isGift(priceController, 142000));
        assertTrue(eventCalendar.isGift(priceController, 120_000));
        assertFalse(eventCalendar.isGift(priceController, 8500));
    }


}
