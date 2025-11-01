package lotto.ui;

import lotto.domain.Lotto;
import lotto.domain.PurchasedLotto;

public class OutputView {
    public void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        int ticketAmount = purchasedLotto.getTicketAmount();
        System.out.println(ticketAmount + "개를 구매했습니다.");
        for (Lotto lotto: purchasedLotto.getPurchasedLotto()) {
            System.out.println(lotto);
        }
    }
}
