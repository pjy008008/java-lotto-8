package lotto.domain;

import java.util.List;

public record PurchasedLotto(List<Lotto> purchasedLotto) {
    public int getTicketAmount() {
        return purchasedLotto.size();
    }
}
