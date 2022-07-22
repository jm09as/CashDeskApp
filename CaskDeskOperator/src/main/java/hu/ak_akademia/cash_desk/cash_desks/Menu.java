package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MenuOptions;
import hu.ak_akademia.cash_desk_main.MenuSercivePoint;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

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
			public List<String> process(List<String> list, CashDesk cashD) {
				return null;
			}
		};
	}

	private static final MenuOption EXIT;
	private static final List<MenuOption> ALL_MENU_OPTIONS = getAllMenu(); //
	private static final Menu INSTANCE = new Menu();
	private static final String SELECT_ALL = "SELECT * FROM cash_desks.cash_desk";

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

	@Override
	public MenuOption getMenuOption(MenuOptions moEnum) {
		return switch (moEnum) {
			case CREATE -> new CreateNewCashDeskOption(); //
			case LOAD -> new LoadCashDeskOption(); //
			case DELETE -> new DeleteCashDeskOption(); //
			case REGISTRY -> new RegistryManagmentOption(); //
			case QUIT -> EXIT;
		};
	}

	@Override
	public List<CashDesk> getAllCashDesk() {
		List<CashDesk> list = new ArrayList<>();
		try (PreparedStatement ps = MySQLUtils.getMySQLConnection().prepareStatement(SELECT_ALL)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(CashDesk.builder() //
						.withCashDeskName(rs.getString(2)) //
						.withIdNumber(rs.getInt(1)) //
						.withLimit(rs.getInt(3)) //
						.withEntryTime((LocalDateTime) rs.getObject(4)) //
						.build());
			}
		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public CashDesk getLastModification(List<CashDesk> list) {
		Comparator<LocalDateTime> ldt = LocalDateTime::compareTo;
		return list.stream().sorted((cd1, cd2) -> ldt.compare(cd1.getEntryTime(), cd2.getEntryTime())) //
				.toList().get(list.size() - 1);
	}

}
