package seol.study.lotto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import seol.study.lotto.vo.AutoLottoResultVo;
import seol.study.lotto.vo.LottoNumberVo;

class AutoLottoTest {

	private AutoLottoInputViewer inputViewer = new AutoLottoInputViewer();
	private AutoLottoResultViewer resultViewer = new AutoLottoResultViewer();
	private AutoLottoPublisher publisher = new AutoLottoPublisher();
	private AutoLottoResultChecker resultChecker = new AutoLottoResultChecker();

	@Test
	public void autoLotto_구입() {
		// given
		String buyAmount = "14000";
		// when
		int buyCount = inputViewer.buyLotto(new Scanner(buyAmount+"\n"));
		// then
		assertEquals(14, buyCount);
	}

	@Test
	public void autoLotto_로또번호_발급() {
	  // given
		int buyCount = 5;

		// when
		List<LottoNumberVo> lottoList = publisher.publish(buyCount);

	  // then
		assertEquals(5, lottoList.size());
		lottoList.forEach(System.out::println);
	}

	@Test
	public void autoLotto_당첨번호_입력() {
	  // given
		String inputNumberText = "1, 2, 3, 4, 5, 6";

	  // when
		LottoNumberVo winningNumber = inputViewer.inputWinningNumber(new Scanner(inputNumberText + "\n"));

		// then
		List<Integer> inputNumbers = Arrays.stream(inputNumberText.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		winningNumber.getNumbers().forEach(number -> assertTrue(inputNumbers.contains(number)));
	}

	@Test
	public void autoLotto_당첨_통계() {
		// given
		List<LottoNumberVo> lottoList = new ArrayList<>();
		lottoList.add(new LottoNumberVo(Arrays.asList(1, 2, 3, 4, 44, 45))); // 3등
		lottoList.add(new LottoNumberVo(Arrays.asList(1, 2, 3, 43, 44, 45))); // 4등

		LottoNumberVo winningNumber = new LottoNumberVo(Arrays.asList(1, 2, 3, 4, 5, 6)); // 당첨번호
		int buyAmount = 14000; // 구매금액

		// when
		AutoLottoResultVo resultVo = resultChecker.check(lottoList, winningNumber);
		Map<LottoGrade, Integer> winningInfo = resultVo.getWinningInfo();

		// then
		assertEquals(0, winningInfo.get(LottoGrade.FIRST));
		assertEquals(0, winningInfo.get(LottoGrade.SECOND));
		assertEquals(1, winningInfo.get(LottoGrade.THIRD));
		assertEquals(1, winningInfo.get(LottoGrade.FOURTH));

		// 출력
		resultViewer.viewWinningResult(resultVo);
	}

	@Test
	public void autoLotto_통합테스트() {
		String buyAmountText = "14000";
		String winningNumberText = "1, 2, 3, 4, 5, 6";
		int buyCount = inputViewer.buyLotto(new Scanner(buyAmountText+"\n"));
		List<LottoNumberVo> lottoList = publisher.publish(buyCount);
		LottoNumberVo winningNumber = inputViewer.inputWinningNumber(new Scanner(winningNumberText + "\n"));
		AutoLottoResultVo resultVo = resultChecker.check(lottoList, winningNumber);
		resultViewer.viewWinningResult(resultVo);
	}

	@Test
	public void collectionsShuffleTest() {
		String[] array = {"data1", "data2", "data3", "data4", "data5"};

		Collections.shuffle(Arrays.asList(array));

		for (String value : array) {
			System.out.print(value + " ");
		}
	}
}