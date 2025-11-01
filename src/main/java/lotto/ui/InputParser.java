package lotto.ui;

import lotto.domain.Lotto;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final String DELIMITER = ",";
    private static final String ERROR_PURCHASE_AMOUNT_REQUIRED = "구입 금액을 입력해야 합니다.";
    private static final String ERROR_PURCHASE_AMOUNT_NOT_NUMBER = "구입 금액은 숫자 형식이어야 합니다.";
    private static final String ERROR_WINNING_NUMBERS_REQUIRED = "당첨 번호를 입력해야 합니다.";
    private static final String ERROR_WINNING_NUMBERS_NOT_NUMBER = "당첨 번호는 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_REQUIRED = "보너스 번호를 입력해야 합니다.";
    private static final String ERROR_BONUS_NUMBER_NOT_NUMBER = "보너스 번호는 숫자여야 합니다.";

    int parsePurchaseAmount(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_REQUIRED);
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_NOT_NUMBER);
        }
    }

    Lotto parseWinningNumbers(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_WINNING_NUMBERS_REQUIRED);
        }
        List<Integer> winningNumbers = Arrays.stream(input.split(DELIMITER))
                .map(this::parseNumber)
                .toList();

        return new Lotto(winningNumbers);
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_WINNING_NUMBERS_NOT_NUMBER);
        }
    }

    int parseBonusNumber(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_REQUIRED);
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_NOT_NUMBER);
        }
    }
}
