package christmas;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;

public class InputController {

    public List<String> seperateComma(String input){
        // 리스트 필터 돌려서 공백과 콤마 다 잡아내고 사이즈 비교해서 다르면 에러
        List<String> seperated = Arrays.asList(input.split(",", -1));
        int blankCount = (int)seperated.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return seperated;
    }


    public WootecoMenu inputToWootechMenu(String seperatedInput){
        String[] splitInput = seperatedInput.split("-");
        validateNotHypen(splitInput);
        validateNumber(splitInput[1]);
        return new WootecoMenu(splitInput[0], parseInt(splitInput[1]));
    }

    private void validateNotHypen(String[] splitInput){
        if (splitInput.length < 2){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNumber(String number){
        try{
            parseInt(number);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
