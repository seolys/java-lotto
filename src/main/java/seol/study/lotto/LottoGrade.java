package seol.study.lotto;

import java.util.Arrays;

public enum LottoGrade {
	FIRST(6, 2000000000),
	SECOND(5, 1500000),
	THIRD(4, 50000),
	FOURTH(3, 5000),
	FAIL(0, 0)
	;
	/** 매칭 조건 */
	private final int matchCount;
	/** 당첨 금액 */
	private final int prizeMoney;

	LottoGrade(int matchCount, int prizeMoney) {
		this.matchCount = matchCount;
		this.prizeMoney = prizeMoney;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getPrizeMoney() {
		return prizeMoney;
	}

	public static LottoGrade valueOfMatchCount(long matchCount) {
		return Arrays.stream(values())
				.filter(grade -> grade.matchCount == matchCount)
				.findFirst()
				.orElse(FAIL);
	}
}
