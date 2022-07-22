package hu.ak_akademia.cash_desk_main;

import java.util.List;

public interface MenuSercivePoint {

	List<MenuOption> getAllMenuOptions();

	MenuOption getMenuOption(int i);

	MenuOption getMenuOption(MenuOptions moEnum);

	CashDesk getLastModification(List<CashDesk> list);

	public List<CashDesk> getAllCashDesk();
}
