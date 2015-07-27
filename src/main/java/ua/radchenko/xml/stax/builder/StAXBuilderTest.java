package ua.radchenko.xml.stax.builder;

public class StAXBuilderTest {

	public static void main(String[] args) {
		AccountsStAXBuilder staxBuilder = new AccountsStAXBuilder();
		staxBuilder.buildSetAccounts("data_sax_validator/accounts.xml");
		System.out.println(staxBuilder.getAccounts());

	}

}
