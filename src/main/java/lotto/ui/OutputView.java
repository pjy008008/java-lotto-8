package lotto.ui;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;

import java.util.*;

import static lotto.domain.LottoRank.*;

public class OutputView {
    private static final String PURCHASED_LOTTO_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String RESULT_HEADER = "\n당첨 통계\n---";
    private static final String RESULT_FORMAT = "%s (%,d원) - %d개";
    private static final String PROFIT_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        int ticketAmount = purchasedLotto.getTicketAmount();
        String displayFormat = String.format(PURCHASED_LOTTO_MESSAGE,
                ticketAmount);
        System.out.println(displayFormat);
        for (Lotto lotto: purchasedLotto.getPurchasedLotto()) {
            System.out.println(lotto);
        }
    }

    public void printResult(Map<LottoRank, Integer> result) {
        System.out.println(RESULT_HEADER);
        List<LottoRank> ranksToDisplay = new ArrayList<>(
                List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST));
        for (LottoRank rank : ranksToDisplay) {
            String displayFormat = String.format(RESULT_FORMAT,
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
        String displayFormat = String.format(PROFIT_MESSAGE,
                ((double) sum / purchaseAmount) * 100);
        System.out.println(displayFormat);
    }
}
