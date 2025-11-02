package lotto.ui;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Nested
    @DisplayName("parsePurchaseAmount 메서드는")
    class Describe_parsePurchaseAmount {

        @Test
        @DisplayName("유효한 숫자 문자열을 정수로 올바르게 변환해야 한다.")
        void shouldParseValidNumberString() {
            // given
            String input = "8000";

            // when
            int amount = inputParser.parsePurchaseAmount(input);

            // then
            assertThat(amount).isEqualTo(8000);
        }

        @Test
        @DisplayName("숫자 형식의 문자열에 공백이 있어도 정수로 변환해야 한다.")
        void shouldParseValidNumberStringWithSpaces() {
            // given
            String input = " 5000 ";

            // when
            int amount = inputParser.parsePurchaseAmount(input);

            // then
            assertThat(amount).isEqualTo(5000);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        @DisplayName("비어 있거나 공백만 있는 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForBlankInput(String blankInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseAmount(blankInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("구입 금액을 입력해야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "1000원", " 1,000"})
        @DisplayName("숫자가 아닌 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForNonNumericInput(String nonNumericInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parsePurchaseAmount(nonNumericInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("숫자 형식이어야 합니다.");
        }
    }

    @Nested
    @DisplayName("parseWinningNumbers 메서드는")
    class Describe_parseWinningNumbers {

        @Test
        @DisplayName("쉼표로 구분된 유효한 숫자 문자열을 Lotto 객체로 변환해야 한다.")
        void shouldParseValidWinningNumbers() {
            // given
            String input = "1,2,3,4,5,6";
            // when
            Lotto lotto = inputParser.parseWinningNumbers(input);

            // then
            assertThat(lotto).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        }

        @Test
        @DisplayName("숫자 주변에 공백이 있어도 올바르게 Lotto 객체로 변환해야 한다.")
        void shouldParseWinningNumbersWithSpaces() {
            // given
            String input = " 1, 2 ,3,4, 5 ,6 ";

            // when
            Lotto lotto = inputParser.parseWinningNumbers(input);

            // then
            assertThat(lotto).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        @DisplayName("비어 있거나 공백만 있는 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForBlankInput(String blankInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(blankInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("당첨 번호를 입력해야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,a,4,5,6", "1, 2, 3.5, 4, 5, 6"})
        @DisplayName("숫자가 아닌 값이 포함된 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForNonNumericValues(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("당첨 번호는 숫자여야 합니다.");
        }

        @Test
        @DisplayName("쉼표 사이에 빈 값이 있는 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForEmptyValueBetweenCommas() {
            // given
            String input = "1,2,,4,5,6";

            // when & then
            assertThatThrownBy(() -> inputParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("당첨 번호는 숫자여야 합니다.");
        }
    }

    @Nested
    @DisplayName("parseBonusNumber 메서드는")
    class Describe_parseBonusNumber {

        @Test
        @DisplayName("유효한 숫자 문자열을 정수로 올바르게 변환해야 한다.")
        void shouldParseValidNumberString() {
            // given
            String input = "7";

            // when
            int bonusNumber = inputParser.parseBonusNumber(input);

            // then
            assertThat(bonusNumber).isEqualTo(7);
        }

        @Test
        @DisplayName("숫자 주변에 공백이 있어도 정수로 변환해야 한다.")
        void shouldParseValidNumberStringWithSpaces() {
            // given
            String input = " 8 ";

            // when
            int bonusNumber = inputParser.parseBonusNumber(input);

            // then
            assertThat(bonusNumber).isEqualTo(8);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "   "})
        @DisplayName("비어 있거나 공백만 있는 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForBlankInput(String blankInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parseBonusNumber(blankInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("보너스 번호를 입력해야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"abc", "7a"})
        @DisplayName("숫자가 아닌 문자열은 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForNonNumericInput(String nonNumericInput) {
            // when & then
            assertThatThrownBy(() -> inputParser.parseBonusNumber(nonNumericInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("보너스 번호는 숫자여야 합니다.");
        }
    }
}