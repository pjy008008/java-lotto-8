package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Nested
    @DisplayName("Lotto 객체 생성 시")
    class Describe_Constructor {

        @Test
        @DisplayName("유효한 로또 번호 리스트로 성공적으로 생성되어야 한다.")
        void shouldCreateLottoSuccessfullyWithValidNumbers() {
            // given
            List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);

            // when
            Lotto lotto = new Lotto(validNumbers);

            // then
            assertThat(lotto).isNotNull();
        }

        @Test
        @DisplayName("번호 개수가 6개가 아니면 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionWhenSizeIsNotSix_More() {
            // given
            List<Integer> invalidSizeList = List.of(1, 2, 3, 4, 5, 6, 7);

            // when & then
            assertThatThrownBy(() -> new Lotto(invalidSizeList))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("중복된 번호가 포함되면 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionWhenNumbersAreDuplicated() {
            // given
            List<Integer> duplicatedList = List.of(1, 2, 3, 4, 5, 5);

            // when & then
            assertThatThrownBy(() -> new Lotto(duplicatedList))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("번호가 1~45 범위를 벗어나면(0 포함) IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionWhenNumberIsOutOfRange_Less() {
            // given
            List<Integer> outOfRangeList = List.of(0, 2, 3, 4, 5, 6);

            // when & then
            assertThatThrownBy(() -> new Lotto(outOfRangeList))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1에서 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("번호가 1~45 범위를 벗어나면(46 포함) IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionWhenNumberIsOutOfRange_More() {
            // given
            List<Integer> outOfRangeList = List.of(1, 2, 3, 4, 5, 46);

            // when & then
            assertThatThrownBy(() -> new Lotto(outOfRangeList))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1에서 45 사이여야 합니다.");
        }
    }

    @Nested
    @DisplayName("has 메서드는")
    class Describe_has {

        @Test
        @DisplayName("로또 번호에 포함된 숫자인 경우 true를 반환해야 한다.")
        void shouldReturnTrueWhenNumberExists() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // when
            boolean result = lotto.has(3);

            // then
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("로또 번호에 포함되지 않은 숫자인 경우 false를 반환해야 한다.")
        void shouldReturnFalseWhenNumberDoesNotExist() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // when
            boolean result = lotto.has(7);

            // then
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("calculateMatchCount 메서드는")
    class Describe_calculateMatchCount {
        private final Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        @Test
        @DisplayName("일치하는 번호가 없을 때 0을 반환해야 한다.")
        void shouldReturnZeroWhenNoNumbersMatch() {
            // given
            Lotto otherLotto = new Lotto(List.of(10, 11, 12, 13, 14, 15));

            // when
            int matchCount = lotto.calculateMatchCount(otherLotto);

            // then
            assertThat(matchCount).isEqualTo(0);
        }

        @Test
        @DisplayName("일부 번호가 일치할 때 정확한 개수를 반환해야 한다.")
        void shouldReturnCorrectCountWhenSomeNumbersMatch() {
            // given
            Lotto otherLotto = new Lotto(List.of(1, 2, 3, 13, 14, 15));

            // when
            int matchCount = lotto.calculateMatchCount(otherLotto);

            // then
            assertThat(matchCount).isEqualTo(3);
        }

        @Test
        @DisplayName("모든 번호가 일치할 때 6을 반환해야 한다.")
        void shouldReturnSixWhenAllNumbersMatch() {
            // given
            Lotto otherLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // when
            int matchCount = lotto.calculateMatchCount(otherLotto);

            // then
            assertThat(matchCount).isEqualTo(6);
        }
    }
}
