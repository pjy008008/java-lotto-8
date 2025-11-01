package lotto.controller;

import lotto.AppConfig;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.PurchasedLotto;
import lotto.service.LottoGameService;
import lotto.service.LottoMachine;
import lotto.ui.InputView;
import lotto.ui.OutputView;

import java.util.Map;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;
    private final LottoGameService lottoGameService;

    public LottoController(AppConfig appConfig) {
        this.inputView = appConfig.inputView();
        this.outputView = appConfig.outputView();
        this.lottoMachine = appConfig.lottoMachine();
        this.lottoGameService = appConfig.lottoGameService();
    }

    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();

        PurchasedLotto purchasedLotto = lottoMachine.issueTickets(purchaseAmount);

        outputView.printPurchasedLotto(purchasedLotto);

        Lotto winningLotto = inputView.getWinningNumbers();
        int bonusNumber = inputView.getBonusNumber(winningLotto);

        Map<LottoRank, Integer> result = lottoGameService.calculateResult(purchasedLotto, winningLotto, bonusNumber);

        outputView.printResult(result);
        outputView.printProfit(result, purchaseAmount);
    }
}
