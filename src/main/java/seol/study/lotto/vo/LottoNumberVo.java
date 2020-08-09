package seol.study.lotto.vo;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberVo {
	List<Integer> numbers = new ArrayList<>();

	public LottoNumberVo(List<Integer> lottoNumbers) {
		long count = getValidNumberCount(lottoNumbers);
		if (count != 6)
			throw new RuntimeException();
		this.numbers = lottoNumbers;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public boolean contains(Integer i) {
		return numbers.contains(i);
	}

	private long getValidNumberCount(List<Integer> lottoNumbers) {
		return lottoNumbers.stream()
					.filter(this::isValidNumber)
					.count();
	}

	private boolean isValidNumber(Integer number) {
		return  number != null && number > 0 && number < 46;
	}


}
