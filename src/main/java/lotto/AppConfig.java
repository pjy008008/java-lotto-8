package lotto;

import lotto.domain.LottoGenerator;
import lotto.domain.RandomLottoGenerator;
import lotto.service.LottoGameService;
import lotto.service.LottoMachine;
import lotto.ui.InputView;
import lotto.ui.OutputView;

public class AppConfig {
    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LottoGenerator lottoGenerator() {
        return new RandomLottoGenerator();
    }

    public LottoMachine lottoMachine() {
        return new LottoMachine(lottoGenerator());
    }

    public LottoGameService lottoGameService() {
        return new LottoGameService();
    }
}
