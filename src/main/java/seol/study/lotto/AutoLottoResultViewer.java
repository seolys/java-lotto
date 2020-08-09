package seol.study.lotto;

import static seol.study.lotto.LottoConstants.LOTTO_PRICE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import seol.study.lotto.vo.AutoLottoResultVo;

public class AutoLottoResultViewer {
	public void viewWinningResult(AutoLottoResultVo result) {
		System.out.println("당첨 통계");
		System.out.println("--------------");
		printWinningCount(result);
		printWinningRate(result);
	}

	private void printWinningCount(AutoLottoResultVo result) {
		Map<LottoGrade, Integer> winningInfo = result.getWinningInfo();
		Arrays.stream(LottoGrade.values())
				.filter(grade -> grade != LottoGrade.FAIL)
				.sorted(Comparator.reverseOrder())
				.map(grade -> String.format("%s개 일치 (%s원)- %s개", grade.getMatchCount(), grade.getPrizeMoney(), winningInfo.getOrDefault(grade, 0)))
				.forEach(System.out::println);
	}

	private void printWinningRate(AutoLottoResultVo resultVo) {
		int totalWinningAmount = calcTotalWinningAmount(resultVo.getWinningInfo());
		int buyAmount = LOTTO_PRICE * resultVo.getBuyCount();

		String winningRate = calcWinningRate(buyAmount, totalWinningAmount);
		System.out.println(String.format("총 수익률은 %s입니다.", winningRate));
	}

	private String calcWinningRate(int buyAmount, int totalWinningAmount) {
		if (totalWinningAmount <= 0) {
			return "0";
		}
		return new BigDecimal((float) totalWinningAmount / buyAmount)
				.setScale(2, RoundingMode.FLOOR)
				.toPlainString();
	}

	private int calcTotalWinningAmount(Map<LottoGrade, Integer> result) {
		return result.keySet().stream()
				.mapToInt(grade -> grade.getPrizeMoney() * result.getOrDefault(grade, 0))
				.sum();
	}

}
