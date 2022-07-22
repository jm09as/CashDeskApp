package hu.ak_akademia_c.cash_desk_registry_management;

import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MenuRegistrySercivePoint;

public class MenuRegistryOptionImpl implements MenuRegistrySercivePoint {

	static {
		EXIT = new MenuOption() {
			@Override
			public String getName() {
				return "vissza a főmenübe";
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
	private static final MenuOption[] ALL_REGISTERY_MANAGEMENT = { //
			new MakeNewRegistry(), //
			new RegistryDelete(), //
			new RegistryToList(), //
			EXIT };

	private static final MenuRegistryOptionImpl INSTANCE = new MenuRegistryOptionImpl();

	private MenuRegistryOptionImpl() {
	}

	public static MenuRegistryOptionImpl getInstance() {
		return INSTANCE;
	}

	@Override
	public MenuOption[] getAllRegistryOptions() {
		return ALL_REGISTERY_MANAGEMENT;
	}

	public MenuOption getRegistry(int number) {
		for (int i = 0; i < ALL_REGISTERY_MANAGEMENT.length; i++) {
			if (number == i) {
				return ALL_REGISTERY_MANAGEMENT[i];
			}
		}
		return null;
	}

//	public void printRegistryMenu() {
//		int i = 1;
//		StringBuilder result = new StringBuilder("%n%s%n".formatted("-- Bejegyzések kezelése --"));
//		for (var rm : ALL_REGISTERY_MANAGEMENT) {
//			result.append("  %d. %s%n".formatted(i++, rm.getName()));
//		}
//		System.out.println(result);
//	}

}
