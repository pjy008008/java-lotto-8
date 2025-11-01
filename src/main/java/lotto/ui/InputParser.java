package lotto.ui;

import lotto.domain.Lotto;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    int parsePurchaseAmount(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("구입 금액을 입력해야 합니다.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자 형식이어야 합니다.");
        }
    }

    Lotto parseWinningNumbers(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("당첨 번호를 입력해야 합니다.");
        }
        List<Integer> winningNumbers = Arrays.stream(input.split(","))
                .map(this::parseNumber)
                .toList();

        return new Lotto(winningNumbers);
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }

    int parseBonusNumber(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("보너스 번호를 입력해야 합니다.");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
    }
}
