package lotto.ui;

import lotto.domain.Lotto;

public class InputValidator {
    private static final String ERROR_PURCHASE_AMOUNT_POSITIVE = "구입 금액은 양수여야 합니다.";
    private static final String ERROR_PURCHASE_AMOUNT_UNIT = "구입 금액은 1000단위여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_RANGE = "보너스 번호는 1에서 45사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private static final int MIN_PURCHASE_AMOUNT = 1;
    private static final int PURCHASE_UNIT = 1000;
    private static final int MIN_BONUS_NUMBER = 1;
    private static final int MAX_BONUS_NUMBER = 45;

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_POSITIVE);
        }
        if (purchaseAmount % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_UNIT);
        }
    }

    public void validateBonusNumber(int bonusNumber, Lotto winningNumbers) {
        if (bonusNumber < MIN_BONUS_NUMBER || bonusNumber > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_RANGE);
        }
        if (winningNumbers.has(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
