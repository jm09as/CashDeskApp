package hu.ak_akademia.cash_desk_main;

import java.util.List;
import java.util.function.BiPredicate;

public interface MenuOption {

	public static final BiPredicate<Integer, Integer> LIMIT_TESTER = (l, s) -> l < s;

	String getName();

	void process(List<String> list, CashDesk cashD);

	CashDesk setup();

	String limitMessage();

	default String limitTester(int limit, int sum) {
		if (LIMIT_TESTER.test(limit, sum)) {
			return "A pénztár túllépte a limitet %,d Ft összeggel!".formatted(sum - limit);
		}
		return "A pénztár a limittől %,d Ft összeggel elmarad.".formatted(Math.abs(sum - limit));
	}

}
