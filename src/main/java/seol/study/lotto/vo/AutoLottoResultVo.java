package seol.study.lotto.vo;

import java.util.List;
import java.util.Map;
import seol.study.lotto.LottoGrade;

public class AutoLottoResultVo {
	private int buyCount = 0;
	private List<LottoNumberVo> lottoList;
	private Map<LottoGrade, Integer> winningInfo;

	public AutoLottoResultVo(List<LottoNumberVo> lottoList, Map<LottoGrade, Integer> winningInfo) {
		this.buyCount = lottoList.size();
		this.lottoList = lottoList;
		this.winningInfo = winningInfo;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public Map<LottoGrade, Integer> getWinningInfo() {
		return winningInfo;
	}
}
