package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;
import lotto.domain.WinningStat;

public class LottoGameService {

    public WinningStat calculateResult(
            PurchasedLotto purchasedLotto,
            Lotto winningLotto,
            int bonusNumber
    ) {
        WinningStat winningStat = new WinningStat();
        for (Lotto lotto : purchasedLotto.purchasedLotto()) {
            updateWinningStat(winningLotto, bonusNumber, lotto, winningStat);
        }
        return winningStat;
    }

    private static void updateWinningStat(Lotto winningLotto, int bonusNumber, Lotto lotto, WinningStat winningStat) {
        int matchCount = lotto.calculateMatchCount(winningLotto);
        boolean bonusMatch = lotto.has(bonusNumber);
        LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);
        winningStat.increment(rank);
    }
}
