package hu.ak_akademia.cash_desk.cash_desks;

import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;

class RegistryManagmentOption extends AbstractMenuOption {

	@Override
	public String getName() {
		return "Bejegyzések kezelése";
	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	@Override
	public void process(List<String> list, CashDesk cashD) {
	}

}
