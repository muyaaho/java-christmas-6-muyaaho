package christmas.domain;

import static christmas.domain.FoodCategory.*;

import java.util.stream.Stream;

public enum MenuBoard {
    MASHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스",5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBECUE_RIP("바비큐립",54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림",5_000, DESSERT),
    ZERO_COLA("제로콜라", 3_000, DRINK),
    RED_WINE("레드와인",60_000, DRINK),
    CHAMPAGNE("샴페인", 25_000, DRINK);

    private final String name;
    private final int price;
    private final FoodCategory category;

    MenuBoard(String name, int price, FoodCategory category){
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public static int getPrice(String foodName){
        return findMenuBoard(foodName).price;
    }

    public static FoodCategory getCategory(String foodName){
        return findMenuBoard(foodName).category;
    }

    private static MenuBoard findMenuBoard(String foodName) {
        return Stream.of(values())
                .filter(menu -> menu.name.equals(foodName))
                .findAny()
                .orElseThrow();
    }

    public static boolean isDrink(String foodName) {
        return getCategory(foodName).equals(DRINK);
    }

}
