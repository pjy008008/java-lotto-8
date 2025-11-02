package lotto.ui;

import lotto.domain.Lotto;

import static lotto.util.LottoConstants.*;

public class InputValidator {
    private static final String ERROR_PURCHASE_AMOUNT_POSITIVE = "구입 금액은 양수여야 합니다.";
    private static final String ERROR_PURCHASE_AMOUNT_UNIT = "구입 금액은 " + PURCHASE_UNIT + "단위여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_RANGE = "보너스 번호는 " + LOTTO_NUMBER_MIN + "에서 " + LOTTO_NUMBER_MAX + "사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private static final int POSITIVE_THRESHOLD = 1;

    public void validatePurchaseAmount(int purchaseAmount) {
        validatePositive(purchaseAmount);
        validateUnit(purchaseAmount);
    }

    private static void validatePositive(int purchaseAmount) {
        if (purchaseAmount < POSITIVE_THRESHOLD) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_POSITIVE);
        }
    }

    private static void validateUnit(int purchaseAmount) {
        if (purchaseAmount % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_UNIT);
        }
    }

    public void validateBonusNumber(int bonusNumber, Lotto winningNumbers) {
        validateRange(bonusNumber);
        validateDuplicate(bonusNumber, winningNumbers);
    }

    private static void validateRange(int bonusNumber) {
        if (bonusNumber < LOTTO_NUMBER_MIN || bonusNumber > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_RANGE);
        }
    }

    private static void validateDuplicate(int bonusNumber, Lotto winningNumbers) {
        if (winningNumbers.has(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
