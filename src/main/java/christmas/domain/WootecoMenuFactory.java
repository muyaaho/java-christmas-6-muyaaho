package christmas.domain;


public class WootecoMenuFactory {

    private final int count;
    private final String name;

    public WootecoMenuFactory(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public WootecoMenu generateor(){
        return new WootecoMenu(name, count, getCategory(name));
    }

    private FoodCategory getCategory(String name){
        return MenuBoard.getCategory(name);
    }

}
