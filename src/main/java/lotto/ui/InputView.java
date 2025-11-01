package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.validation.InputValidator;

import java.util.function.Supplier;

public class InputView {
    private static final InputParser inputParser = new InputParser();
    private static final InputValidator inputValidator = new InputValidator();

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "\n보너스 번호를 입력해 주세요.";

    private <T> T readValidatedInput(String prompt, Supplier<T> parseAndValidate) {
        while (true) {
            System.out.println(prompt);
            try {
                return parseAndValidate.get();
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_PREFIX + e.getMessage());
            }
        }
    }

    public int getPurchaseAmount() {
        return readValidatedInput(PURCHASE_AMOUNT_PROMPT, () -> {
            String input = Console.readLine();
            int purchaseAmount = inputParser.parsePurchaseAmount(input);
            inputValidator.validatePurchaseAmount(purchaseAmount);
            return purchaseAmount;
        });
    }

    public Lotto getWinningNumbers() {
        return readValidatedInput(WINNING_NUMBERS_PROMPT, () -> {
            String input = Console.readLine();
            return inputParser.parseWinningNumbers(input);
        });
    }

    public int getBonusNumber(Lotto winningNumbers) {
        return readValidatedInput(BONUS_NUMBER_PROMPT, () -> {
            String input = Console.readLine();
            int bonusNumber = inputParser.parseBonusNumber(input);
            inputValidator.validateBonusNumber(bonusNumber, winningNumbers);
            return bonusNumber;
        });
    }
}
