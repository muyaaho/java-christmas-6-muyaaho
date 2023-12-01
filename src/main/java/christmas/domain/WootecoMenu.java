package christmas.domain;

public record WootecoMenu(String name, int count, FoodCategory category) {
    @Override
    public String toString() {
        return name +" " + count+"개";
    }
}
