package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
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
            int purchaseAmount;

            if (input.isBlank()) {
                throw new IllegalArgumentException("구입 금액을 입력해야 합니다.");
            }

            try {
                purchaseAmount = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("구입 금액은 숫자 형식이어야 합니다.");
            }

            if (purchaseAmount <= 0) {
                throw new IllegalArgumentException("구입 금액은 양수여야 합니다.");
            }
            if (purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException("구입 금액은 1000단위여야 합니다.");
            }
            return purchaseAmount;
        });
    }

    public Lotto getWinningNumbers() {
        return readValidatedInput("당첨 번호를 입력해 주세요.", () -> {
            String input = Console.readLine();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("당첨 번호를 입력해야 합니다.");
            }
            List<String> split = List.of(input.split(","));
            List<Integer> winningNumbers = new ArrayList<>();

            for (String s : split) {
                if (s.trim().isEmpty()) {
                    throw new IllegalArgumentException("당첨 번호는 공백일 수 없습니다.");
                }
                try {
                    winningNumbers.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
                }
            }
            return new Lotto(winningNumbers);
        });
    }

    public int getBonusNumber(Lotto winningNumbers) {
        return readValidatedInput("보너스 번호를 입력해 주세요.", () -> {
            String input = Console.readLine();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("보너스 번호를 입력해야 합니다.");
            }
            int bonusNumber;
            try {
                bonusNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
            }
            if (bonusNumber < 1 || bonusNumber > 45) {
                throw new IllegalArgumentException("보너스 번호는 1에서 45사이의 숫자여야 합니다.");
            }
            if (winningNumbers.has(bonusNumber)) {
                throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
            return bonusNumber;
        });
    }
}
