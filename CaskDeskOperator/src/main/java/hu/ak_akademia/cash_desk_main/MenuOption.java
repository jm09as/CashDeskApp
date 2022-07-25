package hu.ak_akademia.cash_desk_main;

import java.util.List;
import java.util.function.BiPredicate;

public interface MenuOption {

	public static final BiPredicate<Integer, Integer> LIMIT_TESTER = (l, s) -> l < s;

	String getName();

	void process(List<String> list,CashDesk cashD);

	CashDesk setup();

	default void limitTester(int limit, int sum) {
		if (LIMIT_TESTER.test(limit, sum)) {
			System.out.println("%nTúllépted a megadott limitet a %,d Ft összeggel!%n".formatted(sum - limit));
		}
	}


}
