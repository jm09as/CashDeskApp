package hu.ak_akademia.cash_desk.cash_desks;

import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOptions;

class RegistryManagmentOption extends AbstractMenuOption {

	@Override
	public String getName() {
		return "Bejegyzések kezelése";
	}

//	@Override
//	public void process(UserInput userInput, CashDesk cDesk) {
//		for (var mo = userInput.askRegistryMenu(); !mo.quit(); mo = userInput.askRegistryMenu()) {
//			mo.process(userInput, cDesk);
//			cashDesk = cDesk;
//		}
//	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	@Override
	public int getId() {
		return MenuOptions.REGISTRY.ordinal();
	}

	@Override
	public List<String> process(List<String> list, CashDesk cashD) {
		return null;
	}

}
