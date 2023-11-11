package christmas;

import static java.lang.Integer.parseInt;

public class InputController {

    public void checkHyhpen(String input){

    }

    public WootecoMenu inputToWootechMenu(String input){
        String[] splitInput = input.split("-");
        return new WootecoMenu(splitInput[0], parseInt(splitInput[1]));
    }


}
