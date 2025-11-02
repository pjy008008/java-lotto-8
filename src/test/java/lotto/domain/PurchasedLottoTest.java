package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PurchasedLottoTest {
    private final Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private final Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));

    @Nested
    @DisplayName("getTicketAmount 메서드는")
    class Describe_getTicketAmount {
        @Test
        @DisplayName("구매한 로또의 개수를 정확히 반환해야 한다.")
        void shouldReturnCorrectTicketAmount() {
            // given
            List<Lotto> lottoList = List.of(lotto1, lotto2);
            PurchasedLotto purchasedLotto = new PurchasedLotto(lottoList);

            // when
            int amount = purchasedLotto.getTicketAmount();

            // then
            assertThat(amount).isEqualTo(2);
        }

        @Test
        @DisplayName("구매한 로또가 없을 경우 0을 반환해야 한다.")
        void shouldReturnZeroForEmptyList() {
            // given
            List<Lotto> emptyList = Collections.emptyList();
            PurchasedLotto purchasedLotto = new PurchasedLotto(emptyList);

            // when
            int amount = purchasedLotto.getTicketAmount();

            // then
            assertThat(amount).isEqualTo(0);
        }
    }
}