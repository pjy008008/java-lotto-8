package lotto.ui;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;
import lotto.domain.WinningStat;

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
        for (Lotto lotto: purchasedLotto.purchasedLotto()) {
            System.out.println(lotto);
        }
    }

    public void printResult(WinningStat winningStat) {
        System.out.println(RESULT_HEADER);
        List<LottoRank> ranksToDisplay = new ArrayList<>(
                List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST));
        for (LottoRank rank : ranksToDisplay) {
            String displayFormat = formatResultLine(winningStat, rank);
            System.out.println(displayFormat);
        }
    }

    private static String formatResultLine(WinningStat winningStat, LottoRank rank) {
        return String.format(RESULT_FORMAT,
                rank.getDescription(),
                rank.getPrizeMoney(),
                winningStat.getCount(rank)
        );
    }

    public void printProfit(WinningStat winningStat, int purchaseAmount) {
        double profit = winningStat.calculateProfit(purchaseAmount);
        String displayFormat = String.format(PROFIT_MESSAGE,
                profit);
        System.out.println(displayFormat);
    }
}
