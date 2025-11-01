package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.validation.InputValidator;

import java.util.function.Supplier;

public class InputView {
    private static final InputParser inputParser = new InputParser();
    private static final InputValidator inputValidator = new InputValidator();
    private <T> T readValidatedInput(String prompt, Supplier<T> parseAndValidate) {
        while (true) {
            System.out.println(prompt);
            try {
                return parseAndValidate.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public int getPurchaseAmount() {
        return readValidatedInput("구입금액을 입력해 주세요.", () -> {
            String input = Console.readLine();
            int purchaseAmount = inputParser.parsePurchaseAmount(input);
            inputValidator.validatePurchaseAmount(purchaseAmount);
            return purchaseAmount;
        });
    }

    public Lotto getWinningNumbers() {
        return readValidatedInput("당첨 번호를 입력해 주세요.", () -> {
            String input = Console.readLine();
            return inputParser.parseWinningNumbers(input);
        });
    }

    public int getBonusNumber(Lotto winningNumbers) {
        return readValidatedInput("보너스 번호를 입력해 주세요.", () -> {
            String input = Console.readLine();
            int bonusNumber = inputParser.parseBonusNumber(input);
            inputValidator.validateBonusNumber(bonusNumber, winningNumbers);
            return bonusNumber;
        });
    }
}
