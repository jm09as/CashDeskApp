package hu.ak_akademia.cash_desk_main;

public enum MenuOptions {

	CREATE("Új pénztár felvétele"),
	LOAD("Pénztár betöltése"),
	DELETE("Pénztár törlése"),
	REGISTRY("Bejegyzések kezelése"),
	QUIT("Kilépés");

	private String name;

	MenuOptions(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
