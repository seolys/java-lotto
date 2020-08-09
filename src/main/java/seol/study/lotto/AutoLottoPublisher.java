package seol.study.lotto;

import static java.util.stream.Collectors.toList;
import static seol.study.lotto.LottoConstants.LOTTO_CHOICE_NUMBER;
import static seol.study.lotto.LottoConstants.LOTTO_END_NUMBER;
import static seol.study.lotto.LottoConstants.LOTTO_START_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import seol.study.lotto.vo.LottoNumberVo;

public class AutoLottoPublisher {
	private final List<Integer> lottoNumber;

	public AutoLottoPublisher() {
		this.lottoNumber = IntStream.range(LOTTO_START_NUMBER, LOTTO_END_NUMBER+1)
				.mapToObj(i -> i)
				.collect(toList());
	}

	public List<LottoNumberVo> publish(int buyCount) {
		List<LottoNumberVo> issueList = new ArrayList<>();
		for (int i=0; i<buyCount; i++) {
			issueList.add(makeLottoNumber());
		}
		return issueList;
	}

	private LottoNumberVo makeLottoNumber() {
		List<Integer> lottoNumber = shuffleAndExtractNumber();
		Collections.sort(lottoNumber);
		return new LottoNumberVo(lottoNumber);
	}

	private List<Integer> shuffleAndExtractNumber() {
		List<Integer> shuffleLottoNumbers = new ArrayList<>(this.lottoNumber);
		Collections.shuffle(shuffleLottoNumbers);
		return shuffleLottoNumbers.subList(0, LOTTO_CHOICE_NUMBER);
	}

}
