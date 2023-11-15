package christmas.Controller.Input;

import static java.lang.Integer.parseInt;

import christmas.Domain.GenerateWootecoMenu;
import christmas.Domain.Record.WootecoMenu;
import java.util.Arrays;
import java.util.List;


public class FoodsController {
    private static final String FIRST_SEP = ",";
    private static final String SECOND_SEP = "-";
    Validate validate;

    public FoodsController(Validate validate) {
        this.validate = validate;
    }

    public List<WootecoMenu> setOrderedMenu(String line){
        List<String> dividedCommaList = seperateComma(line);
        return dividedCommaList.stream().map(this::inputToWootechMenu).toList();
    }

    private WootecoMenu inputToWootechMenu(String inputDividedComma){
        String[] dividedHyphen = inputDividedComma.split(SECOND_SEP);
        return new GenerateWootecoMenu(dividedHyphen[0], parseInt(dividedHyphen[1])).generateor();
    }

    private List<String> seperateComma(String inputLine){
        List<String> dividedCommaList = Arrays.asList(inputLine.split(FIRST_SEP, -1));
        return allValidate(dividedCommaList);
    }

    private List<String> allValidate(List<String> dividedCommaList){
        dividedCommaList.forEach(s -> {
            String[] dividedHyphen = s.split(SECOND_SEP);
            validate.hypen(dividedHyphen);
            validate.number(dividedHyphen);
            validate.elementBlank(dividedHyphen);
            validate.inMenu(dividedHyphen);
            validate.zero(dividedHyphen);
        });
        validate.blank(dividedCommaList);
        validate.duplicated(dividedCommaList);
        validate.foodCount(dividedCommaList);
        validate.onlyDrink(dividedCommaList);
        return dividedCommaList;
    }

}
