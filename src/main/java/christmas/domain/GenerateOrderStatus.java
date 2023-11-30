package christmas.domain;


import java.util.List;

public class GenerateOrderStatus {
    private final int day;
    private final List<WootecoMenu> foods;
    private final int totalPrice;

    public GenerateOrderStatus(int day, List<WootecoMenu> foods) {
        this.day = day;
        this.foods = foods;
        this.totalPrice = totalPriceBeforeDiscount();
    }

    public OrderStatus generate(){
        return new OrderStatus(day, foods, totalPrice);
    }

    private int totalPriceBeforeDiscount(){
        int totalPrice = 0;
        for(WootecoMenu food: foods){
            totalPrice += (MenuBoard.getPrice(food.name()) * food.count());
        }
        return totalPrice;
    }

}
