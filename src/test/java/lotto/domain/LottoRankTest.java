package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Nested
    @DisplayName("valueOf 메서드는")
    class Describe_valueOf {

        @Test
        @DisplayName("6개 일치 시 보너스 여부와 관계없이 FIRST를 반환해야 한다.")
        void shouldReturnFirstRankForSixMatches() {
            // when
            LottoRank resultWithBonus = LottoRank.valueOf(6, true);
            LottoRank resultWithoutBonus = LottoRank.valueOf(6, false);

            // then
            assertThat(resultWithoutBonus).isEqualTo(LottoRank.FIRST);
            assertThat(resultWithBonus).isEqualTo(LottoRank.FIRST);
        }

        @Test
        @DisplayName("5개 일치 및 보너스 일치 시 SECOND를 반환해야 한다.")
        void shouldReturnSecondRankForFiveMatchesWithBonus() {
            // when
            LottoRank resultRank = LottoRank.valueOf(5, true);

            // then
            assertThat(resultRank).isEqualTo(LottoRank.SECOND);
        }

        @Test
        @DisplayName("5개 일치 및 보너스 불일치 시 THIRD를 반환해야 한다.")
        void shouldReturnThirdRankForFiveMatchesWithoutBonus() {
            // when
            LottoRank resultRank = LottoRank.valueOf(5, false);

            // then
            assertThat(resultRank).isEqualTo(LottoRank.THIRD);
        }

        @Test
        @DisplayName("4개 일치 시 보너스 여부와 관계없이 FOURTH를 반환해야 한다.")
        void shouldReturnFourthRankForFourMatches() {
            // when
            LottoRank resultWithBonus = LottoRank.valueOf(4, true);
            LottoRank resultWithoutBonus = LottoRank.valueOf(4, false);

            // then
            assertThat(resultWithBonus).isEqualTo(LottoRank.FOURTH);
            assertThat(resultWithoutBonus).isEqualTo(LottoRank.FOURTH);
        }

        @Test
        @DisplayName("3개 일치 시 보너스 여부와 관계없이 FIFTH를 반환해야 한다.")
        void shouldReturnFifthRankForThreeMatches() {
            // when
            LottoRank resultWithBonus = LottoRank.valueOf(3, true);
            LottoRank resultWithoutBonus = LottoRank.valueOf(3, false);

            // then
            assertThat(resultWithBonus).isEqualTo(LottoRank.FIFTH);
            assertThat(resultWithoutBonus).isEqualTo(LottoRank.FIFTH);
        }

        @Test
        @DisplayName("2개 이하 일치 시 보너스 여부와 관계없이 NONE을 반환해야 한다.")
        void shouldReturnNoneRankForTwoOrLessMatches() {
            // when
            LottoRank resultTwo = LottoRank.valueOf(2, true);
            LottoRank resultOne = LottoRank.valueOf(1, false);
            LottoRank resultZero = LottoRank.valueOf(0, true);

            // then
            assertThat(resultTwo).isEqualTo(LottoRank.NONE);
            assertThat(resultOne).isEqualTo(LottoRank.NONE);
            assertThat(resultZero).isEqualTo(LottoRank.NONE);
        }
    }




    @Test
    @DisplayName("getPrizeMoney는 각 등수에 맞는 당첨금을 반환해야 한다.")
    void shouldReturnCorrectPrizeMoney() {
        assertThat(LottoRank.FIRST.getPrizeMoney()).isEqualTo(2_000_000_000L);
        assertThat(LottoRank.SECOND.getPrizeMoney()).isEqualTo(30_000_000L);
        assertThat(LottoRank.THIRD.getPrizeMoney()).isEqualTo(1_500_000L);
        assertThat(LottoRank.FOURTH.getPrizeMoney()).isEqualTo(50_000L);
        assertThat(LottoRank.FIFTH.getPrizeMoney()).isEqualTo(5_000L);
        assertThat(LottoRank.NONE.getPrizeMoney()).isEqualTo(0L);
    }

    @Test
    @DisplayName("getDescription은 각 등수에 맞는 설명을 반환해야 한다.")
    void shouldReturnCorrectDescription() {
        assertThat(LottoRank.FIRST.getDescription()).isEqualTo("6개 일치");
        assertThat(LottoRank.SECOND.getDescription()).isEqualTo("5개 일치, 보너스 볼 일치");
        assertThat(LottoRank.THIRD.getDescription()).isEqualTo("5개 일치");
        assertThat(LottoRank.FOURTH.getDescription()).isEqualTo("4개 일치");
        assertThat(LottoRank.FIFTH.getDescription()).isEqualTo("3개 일치");
        assertThat(LottoRank.NONE.getDescription()).isEqualTo("2개 이하 일치");
    }
}