package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.Controller.DiscountController;
import christmas.Domain.GenerateOrderStatus;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.GenerateWootecoMenu;
import christmas.Domain.Record.WootecoMenu;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountControllerTest {

    DiscountController discountController;

    private OrderStatus makeInput(int pay){
        return new OrderStatus(0, List.of(), pay);
    }

    private OrderStatus makeInput(int day, List<WootecoMenu> orderedItems){
        GenerateOrderStatus generateOrderStatus = new GenerateOrderStatus(day, orderedItems);
        return generateOrderStatus.generate();
    }

    private List<WootecoMenu> makeList(String name, int count){
        return List.of(new GenerateWootecoMenu(name, count).generateor());
    }

    @BeforeEach
    void setUp(){
        discountController = new DiscountController();
    }
    @Test
    void 평일_평일할인(){
        assertEquals(discountController.getWeekdayDiscount(makeInput(3, makeList("초코케이크", 1))), 2023);

        List<WootecoMenu> orderedItems2 = Arrays.asList(
                new GenerateWootecoMenu("초코케이크", 1).generateor(),
                new GenerateWootecoMenu("아이스크림", 1).generateor());
        assertEquals(discountController.getWeekdayDiscount(makeInput(3, orderedItems2)), 4046);
    }

    @Test
    void 주말_평일할인(){
        List<WootecoMenu> orderedItems = makeList("초코케이크", 1);
        assertEquals(discountController.getWeekdayDiscount(makeInput(1, orderedItems)), 0);
    }

    @Test
    void 주말_주말할인(){
        List<WootecoMenu> orderedItems = makeList("티본스테이크", 1);
        assertEquals(discountController.getWeekendDiscount(makeInput(15, orderedItems)), 2023);
        List<WootecoMenu> orderedItems2 = List.of(
                new GenerateWootecoMenu("티본스테이크", 1).generateor(),
                new GenerateWootecoMenu("레드와인", 1).generateor());
        assertEquals(discountController.getWeekendDiscount(makeInput(15, orderedItems2)), 2023);

        List<WootecoMenu> orderedItems3 = List.of(
                new GenerateWootecoMenu("티본스테이크", 2).generateor(),
                new GenerateWootecoMenu("레드와인", 1).generateor());
        assertEquals(discountController.getWeekendDiscount(makeInput(15, orderedItems3)), 4046);
    }

    @Test
    void 평일_주말할인(){
        List<WootecoMenu> orderedItems = List.of(new GenerateWootecoMenu("티본스테이크", 1).generateor());
        assertEquals(discountController.getWeekendDiscount(makeInput(4, orderedItems)), 0);
    }

    @Test
    void 특별할인날짜_특별할인(){
        assertEquals(discountController.getSpecialDiscount(3), 1000);
    }

    @Test
    void 특별할인없는날짜_특별할인(){
        assertEquals(discountController.getSpecialDiscount(28), 0);
    }



    @Test
    void 증정이벤트(){
        assertEquals(discountController.getGiftDiscount(makeInput(142_000)), 25_000);
        assertEquals(discountController.getGiftDiscount(makeInput(120_000)), 25_000);
        assertEquals(discountController.getGiftDiscount(makeInput(8_500)), 0);
    }

    @Test
    void 크리스마스_디데이_할인(){
        assertEquals(discountController.getX_masDiscount(1), 1000);
        assertEquals(discountController.getX_masDiscount(2), 1100);
        assertEquals(discountController.getX_masDiscount(25), 3400);
    }




}
