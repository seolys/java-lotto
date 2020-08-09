package seol.study.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seol.study.caculator.StringAddCalculator;

public class CalculatorTest {
	private StringAddCalculator calculator = new StringAddCalculator();

	@Test
	public void splitAndSum_null_또는_빈문자() {
		String text = null;
		assertEquals(0, splitAndSum(text));
		assertEquals(0, splitAndSum(""));
		assertEquals(0, splitAndSum("0"));
		assertEquals(1, splitAndSum("1"));
	}

	@Test
	public void splitAndSum_숫자하나() {
		assertEquals(1, splitAndSum("1"));
	}

	@Test
	public void splitAndSum_숫자두개_콤마() {
		assertEquals(3, splitAndSum("1,2"));
	}

	@Test
	public void splitAndSum_숫자두개_콜론() {
		assertEquals(6, splitAndSum("1,2:3"));
	}

	@Test
	public void splitAndSum_커스텀구분자() {
		assertEquals(6, splitAndSum("//;\n1;2;3"));
	}

	@Test
	public void splitAndSum_오류_케이스() {
		assertThrows(RuntimeException.class, () -> assertEquals(6, splitAndSum("-1,2,3")));
		assertThrows(RuntimeException.class, () -> assertEquals(6, splitAndSum("한글,테스트")));
	}

	private int splitAndSum(String text) {
		return calculator.splitAndSum(text);
	}


}
