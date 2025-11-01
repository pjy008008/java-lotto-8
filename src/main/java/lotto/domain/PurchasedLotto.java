package lotto.domain;

import java.util.List;

public class PurchasedLotto {
    private final List<Lotto> purchasedLotto;

    public PurchasedLotto(List<Lotto> purchasedLotto) {
        this.purchasedLotto = purchasedLotto;
    }
}
