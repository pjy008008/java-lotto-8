package lotto.ui;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;

import java.util.*;

import static lotto.domain.LottoRank.*;

public class OutputView {
    public void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        int ticketAmount = purchasedLotto.getTicketAmount();
        System.out.println("\n" + ticketAmount + "개를 구매했습니다.");
        for (Lotto lotto: purchasedLotto.getPurchasedLotto()) {
            System.out.println(lotto);
        }
    }

    public void printResult(Map<LottoRank, Integer> result) {
        System.out.println("\n당첨 통계\n---");
        List<LottoRank> ranksToDisplay = new ArrayList<>(
                List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST));
        for (LottoRank rank : ranksToDisplay) {
            String displayFormat = String.format("%s (%,d원) - %d개",
                    rank.getDescription(),
                    rank.getPrizeMoney(),
                    result.get(rank)
            );
            System.out.println(displayFormat);
        }
    }

    public void printProfit(Map<LottoRank, Integer> result, int purchaseAmount) {
        long sum = 0L;
        for (LottoRank rank : result.keySet()) {
            sum += rank.getPrizeMoney() * result.get(rank);
        }
        String displayFormat = String.format("총 수익률은 %.1f%%입니다.",
                ((double) sum / purchaseAmount) * 100);
        System.out.println(displayFormat);
    }
}
