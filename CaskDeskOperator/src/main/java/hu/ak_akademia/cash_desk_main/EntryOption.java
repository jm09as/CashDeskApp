package hu.ak_akademia.cash_desk_main;

import java.util.List;

public interface EntryOption extends MenuOption {

	String getName();

	void process(List<String> list, CashDesk cashD);

	List<Entry> getAllEntry();

	void run(CashDesk cashD);

}
