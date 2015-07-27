package ua.radchenko.xml.sax.builder;

public class SAXBuilderTest {

	public static void main(String[] args) {
		AccountSAXBuilder saxBuilder = new AccountSAXBuilder();
		saxBuilder.buildSetAccounts("data_sax_validator/accounts.xml");
		System.out.println(saxBuilder.getAccounts());

	}

}
