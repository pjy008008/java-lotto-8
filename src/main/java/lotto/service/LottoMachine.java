package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.PurchasedLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    PurchasedLotto issueTickets(int purchaseAmount) {
        int ticketAmount = purchaseAmount / 1000;
        List<Lotto> purchaseLotto = new ArrayList<>();
        for (int i = 0; i < ticketAmount; i++) {
            purchaseLotto.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
        }
        return new PurchasedLotto(purchaseLotto);
    }
}
