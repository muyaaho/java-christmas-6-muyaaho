package christmas;

import christmas.View.InputView;
import christmas.View.OutputView;

public class Application {
    public static void main(String[] args) {
        Process process = new Process(new InputView(), new OutputView());
        process.run();

    }
}
