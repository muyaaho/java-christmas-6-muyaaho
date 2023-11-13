package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Test
    void 크리스마스_디데이_할인(){
        assertEquals(eventCalendar.getX_masDiscount(1), 1000);
        assertEquals(eventCalendar.getX_masDiscount(2), 1100);
        assertEquals(eventCalendar.getX_masDiscount(25), 3400);
    }

    @Test
    void 적용된_이벤트_없음(){
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("타파스", 1),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(eventCalendar.getBenefitList(26, orderedItems), new HashMap<String, Integer>());

        List<WootecoMenu> orderedItems2 = List.of(
                new WootecoMenu("시저샐러드", 1)
        );
        assertEquals(eventCalendar.getBenefitList(1, orderedItems), new HashMap<String, Integer>());
    }

    @Test
    void 적용된_이벤트_크리스마스디데이할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 1300);
        output.put("평일 할인", 0);
        output.put("주말 할인", 0);
        output.put("특별 할인", 0);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(eventCalendar.getBenefitList(4, orderedItems), output);

    }

    @Test
    void 적용된_이벤트_평일할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 2023);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("초코케이크", 1)
        );
        assertEquals(eventCalendar.getBenefitList(31, orderedItems), output);
    }

    @Test
    void 적용된_이벤트_주말할인(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 0);
        output.put("주말 할인", 2023);
        output.put("특별 할인", 0);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(eventCalendar.getBenefitList(30, orderedItems), output);
    }

    @Test
    void 특별할인만(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 0);
        output.put("평일 할인", 0);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 0);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1)
        );
        assertEquals(eventCalendar.getBenefitList(31, orderedItems), output);
    }

    @Test
    void 전체_증정이벤트(){
        Map<String, Integer> output = new HashMap<>();
        output.put("크리스마스 디데이 할인", 1200);
        output.put("평일 할인", 4046);
        output.put("주말 할인", 0);
        output.put("특별 할인", 1000);
        output.put("증정 이벤트", 25000);
        List<WootecoMenu> orderedItems = List.of(
                new WootecoMenu("티본스테이크", 1),
                new WootecoMenu("바비큐립", 1),
                new WootecoMenu("초코케이크", 2),
                new WootecoMenu("제로콜라", 1)
        );
        assertEquals(eventCalendar.getBenefitList(3, orderedItems), output);
    }


}
