package christmas.domain;


import java.util.List;

public class OrderStatusFactory {
    private final int day;
    private final List<WootecoMenu> foods;
    private final int totalPrice;

    public OrderStatusFactory(int day, List<WootecoMenu> foods) {
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
