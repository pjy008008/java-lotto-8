package lotto.ui;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {
    private InputValidator inputValidator;
    private Lotto winningLotto;

    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
        winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @Nested
    @DisplayName("validatePurchaseAmount 메서드는")
    class Describe_validatePurchaseAmount {

        @ParameterizedTest
        @ValueSource(ints = {1000, 2000, 8000, 100000})
        @DisplayName("유효한 구입 금액(1000 단위 양수)에 대해 예외를 발생시키지 않아야 한다.")
        void shouldNotThrowExceptionForValidAmount(int validAmount) {
            // when & then
            assertThatCode(() -> inputValidator.validatePurchaseAmount(validAmount))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1000, -1})
        @DisplayName("0 또는 음수인 구입 금액에 대해 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForNonPositiveAmount(int nonPositiveAmount) {
            // when & then
            assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(nonPositiveAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("구입 금액은 양수여야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(ints = {1001, 999, 1500, 8500})
        @DisplayName("1000원 단위가 아닌 구입 금액에 대해 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForInvalidUnitAmount(int invalidUnitAmount) {
            // when & then
            assertThatThrownBy(() -> inputValidator.validatePurchaseAmount(invalidUnitAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("단위여야 합니다.");
        }
    }

    @Nested
    @DisplayName("validateBonusNumber 메서드는")
    class Describe_validateBonusNumber {

        @ParameterizedTest
        @ValueSource(ints = {7, 10, 45})
        @DisplayName("유효한 보너스 번호에 대해 예외를 발생시키지 않아야 한다.")
        void shouldNotThrowExceptionForValidBonusNumber(int validBonusNumber) {
            // when & then
            assertThatCode(() -> inputValidator.validateBonusNumber(validBonusNumber, winningLotto))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("1~45 범위를 벗어나는 보너스 번호에 대해 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForOutOfRangeBonusNumber(int outOfRangeNumber) {
            // when & then
            assertThatThrownBy(() -> inputValidator.validateBonusNumber(outOfRangeNumber, winningLotto))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("1에서 45사이의 숫자여야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 3, 6})
        @DisplayName("당첨 번호와 중복되는 보너스 번호에 대해 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForDuplicateBonusNumber(int duplicateNumber) {
            // when & then
            assertThatThrownBy(() -> inputValidator.validateBonusNumber(duplicateNumber, winningLotto))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}