package hu.ak_akademia.cash_desk_entry_management;

import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.Entry;
import hu.ak_akademia.cash_desk_main.EntryOption;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MenuRegistrySercivePoint;

public class MenuRegistryOptionImpl implements MenuRegistrySercivePoint {

	static {
		EXIT = new EntryOption() {

			@Override
			public CashDesk setup() {
				return null;
			}

			@Override
			public void process(List<String> list, CashDesk cashD) {
			}

			@Override
			public String getName() {
				return "vissza a főmenübe";
			}

			@Override
			public List<Entry> getAllEntry() {
				return null;
			}

			@Override
			public void run(CashDesk cashD) {
			}

			@Override
			public String limitMessage() {
				return null;
			}
		};
	}

	private static final EntryOption EXIT;
	private static final EntryOption[] ALL_REGISTERY_MANAGEMENT = { //
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

	public EntryOption getRegistry(int number) {
		for (int i = 0; i < ALL_REGISTERY_MANAGEMENT.length; i++) {
			if (number == i) {
				return ALL_REGISTERY_MANAGEMENT[i];
			}
		}
		return null;
	}
	
}
