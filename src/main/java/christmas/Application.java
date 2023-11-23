package christmas;

import christmas.controller.Validate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Process process = new Process(new InputView(), new OutputView(), new Validate());
        process.run();
    }
}
