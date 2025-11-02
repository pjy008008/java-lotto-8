package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.List;

import static lotto.util.LottoConstants.*;

public class RandomLottoGenerator implements LottoGenerator {
    public Lotto generate() {
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT)
                .stream()
                .sorted()
                .toList();
        return new Lotto(lotto);
    }
}