package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomException {

    public CustomException(String s) {
        if (s.equals("주문")){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        if (s.equals("날짜")){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }


}
