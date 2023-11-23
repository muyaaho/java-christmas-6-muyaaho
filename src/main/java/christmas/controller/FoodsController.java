package christmas.controller;

import static java.lang.Integer.parseInt;

import christmas.domain.GenerateWootecoMenu;
import christmas.domain.Record.WootecoMenu;
import java.util.Arrays;
import java.util.List;


public class FoodsController {
    private static final String FIRST_SEP = ",";
    private static final String SECOND_SEP = "-";
    // 외부에서 재할당 될 가능성이 없으므로 private final로 하는 것이 좋을 것 같음!(static을 붙이느냐 아니니냐는 외부에 선언되는지 아닌지)
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
            // 여기도 s대신에 foodName이라던지
            String[] dividedHyphen = s.split(SECOND_SEP);
            validate.hypen(dividedHyphen);
            // 오타 hyphen
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
