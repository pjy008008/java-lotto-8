package lotto.domain;

import java.util.List;

public class StubLottoGenerator implements LottoGenerator{
    private final List<Integer> fixedNumbers;

    public StubLottoGenerator(List<Integer> fixedNumbers) {
        this.fixedNumbers = fixedNumbers;
    }

    @Override
    public Lotto generate() {
        return new Lotto(fixedNumbers.stream().sorted().toList());
    }
}
