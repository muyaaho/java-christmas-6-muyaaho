package christmas.Domain;

import christmas.Domain.MenuBoard;

public class WootecoMenu {

    private int count;
    private String name;
    private String category;

    public WootecoMenu(String name, int count) {

        this.name = name;
        this.count = count;
        this.category = getCategory(name);
    }

    private String getCategory(String name){
        return MenuBoard.getCategory(name);
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name +" " + count+"개";
    }
}
