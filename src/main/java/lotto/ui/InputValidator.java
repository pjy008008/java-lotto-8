package lotto.ui;

import lotto.domain.Lotto;

import static lotto.util.LottoConstants.*;

public class InputValidator {
    private static final String ERROR_PURCHASE_AMOUNT_POSITIVE = "구입 금액은 양수여야 합니다.";
    private static final String ERROR_PURCHASE_AMOUNT_UNIT = "구입 금액은 " + PURCHASE_UNIT + "단위여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_RANGE = "보너스 번호는 " + LOTTO_NUMBER_MIN + "에서 " + LOTTO_NUMBER_MAX + "사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private static final int MIN_PURCHASE_AMOUNT = 1;

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_POSITIVE);
        }
        if (purchaseAmount % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_UNIT);
        }
    }

    public void validateBonusNumber(int bonusNumber, Lotto winningNumbers) {
        if (bonusNumber < LOTTO_NUMBER_MIN || bonusNumber > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_RANGE);
        }
        if (winningNumbers.has(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
