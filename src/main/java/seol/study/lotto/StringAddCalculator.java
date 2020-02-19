package seol.study.lotto;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

	public int splitAndSum(String text) {
		if (isEmpty(text))
			return 0;

		String[] tokens = split(text);
		return sum(tokens);
	}

	private String[] split(String text) {
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		if (m.find()) {
			String customDelimiter = m.group(1);
			return m.group(2).split(customDelimiter);
		}
		return text.split(",|:");
	}

	private boolean isEmpty(String text) {
		return text == null || "".equals(text);
	}

	private int parsePositiveInteger(String text) {
		int i = parseInt(text);
		if (isMinus(i))
			throw new RuntimeException(text + " is negative integer");
		return i;
	}

	private int parseInt(String text) {
		try {
			return Integer.parseInt(text);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	private boolean isMinus(int i) {
		return i < 0;
	}


	private int sum(String[] tokens) {
		return Arrays.stream(tokens)
				.map(this::parsePositiveInteger)
				.reduce(Integer::sum)
				.orElse(0);
	}

}
