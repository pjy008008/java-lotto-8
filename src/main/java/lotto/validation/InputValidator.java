package lotto.validation;

import lotto.domain.Lotto;

public class InputValidator {
    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수여야 합니다.");
        }
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("구입 금액은 1000단위여야 합니다.");
        }
    }

    public void validateBonusNumber(int bonusNumber, Lotto winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1에서 45사이의 숫자여야 합니다.");
        }
        if (winningNumbers.has(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
