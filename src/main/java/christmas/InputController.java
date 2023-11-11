package christmas;

import static java.lang.Integer.parseInt;

import java.util.regex.PatternSyntaxException;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;

public class InputController {

    public void checkHyhpen(String input){

    }

    public WootecoMenu inputToWootechMenu(String input){
        String[] splitInput = input.split("-");
        validateNotHypen(splitInput);
        return new WootecoMenu(splitInput[0], parseInt(splitInput[1]));
    }

    private void validateNotHypen(String[] splitInput){
        if (splitInput.length < 2){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }


}
