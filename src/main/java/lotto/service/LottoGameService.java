package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;

import java.util.HashMap;
import java.util.Map;

public class LottoGameService {

    public Map<LottoRank, Integer> calculateResult(
            PurchasedLotto purchasedLotto,
            Lotto winningLotto,
            int bonusNumber
    ) {
        Map<LottoRank, Integer> results = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0);
        }
        for (Lotto lotto : purchasedLotto.purchasedLotto()) {
            int matchCount = lotto.calculateMatchCount(winningLotto);
            boolean bonusMatch = lotto.has(bonusNumber);
            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }
}
