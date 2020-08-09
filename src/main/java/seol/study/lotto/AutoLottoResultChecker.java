package seol.study.lotto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import seol.study.lotto.vo.AutoLottoResultVo;
import seol.study.lotto.vo.LottoNumberVo;

public class AutoLottoResultChecker {

	public AutoLottoResultVo check(List<LottoNumberVo> lottoList, LottoNumberVo winning) {
		Map<LottoGrade, Integer> answer = makeAnswerMap();
		for (LottoNumberVo lotto : lottoList) {
			long matchCount = getMatchCount(lotto, winning);
			accrueCount(answer, matchCount);
		}
		return new AutoLottoResultVo(lottoList, answer);
	}

	private void accrueCount(Map<LottoGrade, Integer> answer, long matchCount) {
		LottoGrade grade = LottoGrade.valueOfMatchCount(matchCount);
		answer.put(grade, answer.get(grade) + 1);
	}

	private Map<LottoGrade, Integer> makeAnswerMap() {
		Map<LottoGrade, Integer> answerMap = new ConcurrentHashMap<>();
		for (LottoGrade value : LottoGrade.values()) {
			answerMap.put(value, 0);
		}
		return answerMap;
	}

	private long getMatchCount(LottoNumberVo lotto, LottoNumberVo winning) {
		return lotto.getNumbers()
				.stream()
				.filter(winning::contains)
				.count();
	}
}
