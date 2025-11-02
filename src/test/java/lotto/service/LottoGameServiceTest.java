package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;
import lotto.domain.WinningStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameServiceTest {
    private LottoGameService lottoGameService;
    private Lotto winningLotto;
    private int bonusNumber;

    @BeforeEach
    void setUp() {
        lottoGameService = new LottoGameService();
        winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        bonusNumber = 7;
    }

    @Test
    @DisplayName("구매한 로또가 여러 등수에 당첨되었을 때 통계를 정확히 계산해야 한다.")
    void shouldCalculateStatisticsForVariousRanks() {
        // given
        Lotto firstPlace = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto thirdPlace = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto fifthPlace = new Lotto(List.of(4, 5, 6, 11, 12, 13));
        Lotto nonePlace = new Lotto(List.of(10, 11, 12, 13, 14, 15));

        PurchasedLotto purchasedLotto = new PurchasedLotto(List.of(
                firstPlace, thirdPlace, fifthPlace, nonePlace
        ));

        // when
        WinningStat winningStat = lottoGameService.calculateResult(purchasedLotto, winningLotto, bonusNumber);

        // then
        assertThat(winningStat).isNotNull();
        assertThat(winningStat.getCount(LottoRank.FIRST)).isEqualTo(1);
        assertThat(winningStat.getCount(LottoRank.SECOND)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.THIRD)).isEqualTo(1);
        assertThat(winningStat.getCount(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(winningStat.getCount(LottoRank.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("구매한 로또가 하나도 당첨되지 않았을 때 통계를 정확히 계산해야 한다.")
    void shouldCalculateStatisticsForNoWins() {
        // given
        Lotto nonePlace1 = new Lotto(List.of(10, 11, 12, 13, 14, 15));
        Lotto nonePlace2 = new Lotto(List.of(1, 8, 9, 10, 11, 12));
        Lotto nonePlace3 = new Lotto(List.of(1, 2, 9, 10, 11, 12));

        PurchasedLotto purchasedLotto = new PurchasedLotto(List.of(nonePlace1, nonePlace2, nonePlace3));

        // when
        WinningStat winningStat = lottoGameService.calculateResult(purchasedLotto, winningLotto, bonusNumber);

        // then
        assertThat(winningStat).isNotNull();
        assertThat(winningStat.getCount(LottoRank.FIRST)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.SECOND)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.THIRD)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.FIFTH)).isEqualTo(0);
        assertThat(winningStat.getCount(LottoRank.NONE)).isEqualTo(3);
    }

    @Test
    @DisplayName("보너스 번호에 따라 2등과 3등을 구분해야 한다.")
    void shouldCorrectlyDistinguishSecondAndThirdPlace() {
        // given
        Lotto secondPlace = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto thirdPlace = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        PurchasedLotto purchasedLotto = new PurchasedLotto(List.of(secondPlace, thirdPlace));

        // when
        WinningStat winningStat = lottoGameService.calculateResult(purchasedLotto, winningLotto, bonusNumber);

        // then
        assertThat(winningStat.getCount(LottoRank.SECOND)).isEqualTo(1);
        assertThat(winningStat.getCount(LottoRank.THIRD)).isEqualTo(1);
    }
}