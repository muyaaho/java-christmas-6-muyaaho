package christmas.Domain;


import java.util.List;

public record OrderStatus(int day, List<WootecoMenu> foods) {

}
