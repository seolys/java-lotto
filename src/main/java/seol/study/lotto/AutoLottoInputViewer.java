package seol.study.lotto;

import static seol.study.lotto.LottoConstants.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import seol.study.lotto.vo.LottoNumberVo;

public class AutoLottoInputViewer {

	public int buyLotto(Scanner scanner) {
		System.out.println("구입금액을 입력해 주세요.");
		try {
			int buyAmount = scanner.nextInt();
			int buyCount = buyAmount / LOTTO_PRICE;
			System.out.println(buyCount + "개를 구매했습니다.");
			return buyCount;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public LottoNumberVo inputWinningNumber(Scanner scanner) {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
		String line = scanner.nextLine();

		List<Integer> winningNumberList = Arrays.stream(line.split(","))
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		return new LottoNumberVo(winningNumberList);
	}
}
