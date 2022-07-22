package hu.ak_akademia.cash_desk.cash_desks;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MenuOptions;
import hu.ak_akademia.cash_desk_main.MenuSercivePoint;

public class Menu implements MenuSercivePoint {

	static {
		EXIT = new MenuOption() {
			@Override
			public String getName() {
				return "Kilépés";
			}

			@Override
			public CashDesk setup() {
				return null;
			}

			@Override
			public int getId() {
				return MenuOptions.QUIT.ordinal();
			}

			@Override
			public List<String> process(List<String> list, CashDesk cashD) {
				return null;
			}

			@Override
			public List<CashDesk> getAllCashDesk() {
				return null;
			}
		};
	}

	private static final MenuOption EXIT;
	private static final List<MenuOption> ALL_MENU_OPTIONS = getAllMenu(); //
	private static final Menu INSTANCE = new Menu();

	private Menu() {
	}

	public static Menu getInstance() {
		return INSTANCE;
	}

	private static List<MenuOption> getAllMenu() {
		return List.of( //
				new CreateNewCashDeskOption(), //
				new LoadCashDeskOption(), //
				new DeleteCashDeskOption(), //
				new RegistryManagmentOption(), //
				EXIT);
	}

	@Override
	public List<MenuOption> getAllMenuOptions() {
		return ALL_MENU_OPTIONS;
	}

	@Override
	public MenuOption getMenuOption(int i) {
		return ALL_MENU_OPTIONS.get(i);
	}

	public MenuOption getMenuOption(MenuOptions moEnum) {
		return switch (moEnum) {
			case CREATE -> new CreateNewCashDeskOption(); //
			case LOAD -> new LoadCashDeskOption(); //
			case DELETE -> new DeleteCashDeskOption(); //
			case REGISTRY -> new RegistryManagmentOption(); //
			case QUIT -> EXIT;
		};
	}
	
	public CashDesk getLastModification(List<CashDesk> list) {
		Comparator<LocalDateTime> ldt = LocalDateTime::compareTo;
		return list.stream().sorted((cd1, cd2) -> ldt.compare(cd1.getEntryTime(), cd2.getEntryTime())) //
				.toList().get(list.size() - 1);
	}

}
