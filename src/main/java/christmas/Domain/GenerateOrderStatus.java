package christmas.Domain;


import java.util.List;
import org.mockito.internal.matchers.Or;

public class GenerateOrderStatus {
    private int day;
    private List<WootecoMenu> foods;
    private int totalCost;

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
            totalAmount += (MenuBoard.getPrice(food.getName()) * food.getCount());
        }
        return totalAmount;
    }

}
