package christmas.domain;


import christmas.domain.Enum.MenuBoard;
import christmas.domain.Record.WootecoMenu;

public class GenerateWootecoMenu {

    private final int count;
    private final String name;

    public GenerateWootecoMenu(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public WootecoMenu generateor(){
        return new WootecoMenu(name, count, getCategory(name));
    }

    private String getCategory(String name){
        return MenuBoard.getCategory(name);
    }

}