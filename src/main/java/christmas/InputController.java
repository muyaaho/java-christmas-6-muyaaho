package christmas;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class InputController {

    public List<String> seperateComma(String input){
        List<String> seperated = Arrays.asList(input.split(",", -1));
        validateBlank(seperated);
        seperated.stream().forEach(s -> {
            validateNotHypen(s);
            validateNumber(s);
            validateElementBlank(s);
        });

        return seperated;
    }


    public WootecoMenu inputToWootechMenu(String seperatedInput){
        validateNotHypen(seperatedInput);
        validateNumber(seperatedInput);
        String[] splitInput = seperatedInput.split("-");
        return new WootecoMenu(splitInput[0], parseInt(splitInput[1]));
    }

    private void validateElementBlank(String element){
        String[] splitInput = element.split("-");
        if (splitInput[0].isBlank() || splitInput[1].isBlank()){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNotHypen(String seperatedInput){
        String[] splitInput = seperatedInput.split("-");
        System.out.println(Arrays.toString(splitInput));
        if (splitInput.length != 2){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNumber(String seperatedInput){
        String[] splitInput = seperatedInput.split("-");
        try{
            parseInt(splitInput[1]);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateBlank(List<String> seperated) {
        int blankCount = (int)seperated.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
