package ua.radchenko.xml.stax.builder;

public enum AccountEnum {
	ACCOUNTS("accounts"), LOGIN("login"), EMAIL("email"), ACCOUNT("account"), NAME(
			"name"), TELEPHONE("telephone"), COUNTRY("country"), CITY("city"), STREET(
			"street"), ADDRESS("address");
	private String value;

	private AccountEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
