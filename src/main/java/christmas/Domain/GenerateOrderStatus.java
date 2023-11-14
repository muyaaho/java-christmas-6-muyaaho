package christmas.Domain;


import christmas.Domain.Enum.MenuBoard;
import christmas.Domain.Record.OrderStatus;
import christmas.Domain.Record.WootecoMenu;
import java.util.List;

public class GenerateOrderStatus {
    private final int day;
    private final List<WootecoMenu> foods;
    private final int totalCost;

    public GenerateOrderStatus(int day, List<WootecoMenu> foods) {
        this.day = day;
        this.foods = foods;
        this.totalCost = totalAmountBeforeDiscount();
    }

    public OrderStatus generate(){
        return new OrderStatus(day, foods, totalCost);
    }

    private int totalAmountBeforeDiscount(){
        int totalAmount = 0;
        for(WootecoMenu food: foods){
            totalAmount += (MenuBoard.getPrice(food.name()) * food.count());
        }
        return totalAmount;
    }

}
