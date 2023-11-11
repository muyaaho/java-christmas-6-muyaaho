package christmas;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class InputController {

    public List<String> seperateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(",", -1));
        validateBlank(dividedCommaList);
        dividedCommaList.stream().forEach(s -> {
            validateNotHypen(s);
            validateNumber(s);
            validateElementBlank(s);
        });

        return dividedCommaList;
    }


    public WootecoMenu inputToWootechMenu(String inputDividedComma){
        validateNotHypen(inputDividedComma);
        validateNumber(inputDividedComma);
        String[] dividedHyphen = inputDividedComma.split("-");
        return new WootecoMenu(dividedHyphen[0], parseInt(dividedHyphen[1]));
    }

    private void validateElementBlank(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        if (dividedHyphen[0].isBlank() || dividedHyphen[1].isBlank()){
            new CustomException("주문");
        }
    }

    private void validateNotHypen(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        if (dividedHyphen.length != 2){
            new CustomException("주문");
        }
    }

    private void validateNumber(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split("-");
        try{
            parseInt(dividedHyphen[1]);
        } catch (NumberFormatException e){
            new CustomException("주문");
        }
    }

    private void validateBlank(List<String> divitedCommaList) {
        int blankCount = (int)divitedCommaList.stream().filter(e -> e.isBlank()).count();
        if (blankCount>0){
            new CustomException("주문");
        }
    }

}
