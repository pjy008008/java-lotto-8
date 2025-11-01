package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningStat {
    private final Map<LottoRank, Integer> stats;

    public WinningStat() {
        stats = new EnumMap<>(LottoRank.class);
        for (LottoRank rank : LottoRank.values()) {
            stats.put(rank, 0);
        }
    }

    public void increment(LottoRank rank) {
        stats.put(rank, stats.get(rank) + 1);
    }

    public int getCount(LottoRank rank) {
        return stats.get(rank);
    }

    public double calculateProfit(int purchaseAmount) {
        long totalPrize = 0L;
        for (LottoRank rank : stats.keySet()) {
            totalPrize += rank.getPrizeMoney() * stats.get(rank);
        }
        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
