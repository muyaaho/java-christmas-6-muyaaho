package christmas.Domain;


import christmas.Domain.Enum.MenuBoard;
import christmas.Domain.Record.WootecoMenu;

public class GenerateWootecoMenu {

    private int count;
    private String name;

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
