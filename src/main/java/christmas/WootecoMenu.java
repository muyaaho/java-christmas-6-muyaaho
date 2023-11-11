package christmas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WootecoMenu {
    private String name;
    private int count;
    List<String> menuboard = new ArrayList<>(Arrays.asList("양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"));
    public WootecoMenu(String name, int count) {
        if (!menuboard.contains(name)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        this.name = name;
        this.count = count;
    }
}
