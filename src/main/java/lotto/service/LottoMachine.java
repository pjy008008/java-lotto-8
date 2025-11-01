package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.PurchasedLotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.LottoConstants.*;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public PurchasedLotto issueTickets(int purchaseAmount) {
        int ticketAmount = purchaseAmount / PURCHASE_UNIT;
        List<Lotto> purchaseLotto = IntStream.range(0, ticketAmount)
                .mapToObj(i -> lottoGenerator.generate())
                .collect(Collectors.toList());
        return new PurchasedLotto(purchaseLotto);
    }
}
