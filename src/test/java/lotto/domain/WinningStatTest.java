package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class WinningStatTest {
    private WinningStat winningStat;

    @BeforeEach
    void setUp() {
        winningStat = new WinningStat();
    }

    @Test
    @DisplayName("생성 시 모든 등수의 횟수가 0으로 초기화되어야 한다.")
    void shouldInitializeWithZeroCountsForAllRanks() {
        // given
        WinningStat newWinningStat = new WinningStat();

        // when & then
        for (LottoRank rank : LottoRank.values()) {
            assertThat(newWinningStat.getCount(rank)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("increment 메서드는 특정 등수의 횟수를 1 증가시켜야 한다.")
    void shouldIncrementRankCountCorrectly() {
        // given
        LottoRank rankToIncrement = LottoRank.FIFTH;
        int initialCount = winningStat.getCount(rankToIncrement);

        // when
        winningStat.increment(rankToIncrement);

        // then
        assertThat(winningStat.getCount(rankToIncrement)).isEqualTo(initialCount + 1);
    }

    @Test
    @DisplayName("increment 메서드를 여러 번 호출하면 횟수가 누적되어야 한다.")
    void shouldAccumulateRankCountCorrectly() {
        // given
        LottoRank rankToIncrement = LottoRank.FOURTH;
        int incrementCount = 3;

        // when
        for (int i = 0; i < incrementCount; i++) {
            winningStat.increment(rankToIncrement);
        }

        // then
        assertThat(winningStat.getCount(rankToIncrement)).isEqualTo(incrementCount);
    }

    @Test
    @DisplayName("getCount 메서드는 현재 등수의 횟수를 정확히 반환해야 한다.")
    void shouldReturnCorrectCount() {
        // given
        winningStat.increment(LottoRank.THIRD);
        winningStat.increment(LottoRank.THIRD);
        winningStat.increment(LottoRank.FIRST);

        // when & then
        assertThat(winningStat.getCount(LottoRank.THIRD)).isEqualTo(2);
        assertThat(winningStat.getCount(LottoRank.FIRST)).isEqualTo(1);
        assertThat(winningStat.getCount(LottoRank.NONE)).isEqualTo(0);
    }

    @Test
    @DisplayName("calculateProfit은 총 당첨금을 기준으로 정확한 수익률을 반환해야 한다.")
    void shouldCalculateProfitCorrectly() {
        // given
        winningStat.increment(LottoRank.FIFTH);
        int purchaseAmount = 8000;

        // when
        double profitRate = winningStat.calculateProfit(purchaseAmount);

        // then
        // (5000.0 / 8000.0) * 100.0 = 62.5
        assertThat(profitRate).isEqualTo(62.5, withPrecision(0.01));
    }

    @Test
    @DisplayName("calculateProfit은 당첨금이 없을 때 0.0%의 수익률을 반환해야 한다.")
    void shouldCalculateZeroProfitWhenNoWins() {
        // given
        winningStat.increment(LottoRank.NONE);
        int purchaseAmount = 1000;

        // when
        double profitRate = winningStat.calculateProfit(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(0.0);
    }
}