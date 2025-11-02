package lotto.service;

import lotto.domain.Lotto;

import lotto.domain.PurchasedLotto;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.StubLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {
    private final LottoGenerator lottoGenerator = new StubLottoGenerator(List.of(1, 2, 3, 4, 5, 6));
    private final LottoMachine lottoMachine = new LottoMachine(lottoGenerator);
    private final Lotto fixedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("구입 금액에 해당하는 정확한 개수의 로또 티켓을 발행해야 한다.")
    void shouldIssueCorrectNumberOfTickets() {
        // given
        int purchaseAmount = 8000;
        int expectedTicketAmount = 8;

        // when
        PurchasedLotto purchasedLotto = lottoMachine.issueTickets(purchaseAmount);

        // then
        assertThat(purchasedLotto.getTicketAmount()).isEqualTo(expectedTicketAmount);
    }

    @Test
    @DisplayName("발행된 로또 티켓들은 Generator가 생성한 내용과 일치해야 한다.")
    void shouldIssueTicketsWithCorrectContentFromGenerator() {
        // given
        int purchaseAmount = 8000;
        int expectedTicketAmount = 8;

        // when
        PurchasedLotto purchasedLotto = lottoMachine.issueTickets(purchaseAmount);

        // then
        assertThat(purchasedLotto.purchasedLotto()).hasSize(expectedTicketAmount);
        assertThat(purchasedLotto.purchasedLotto()).allMatch(lotto -> lotto.equals(fixedLotto));
    }
}