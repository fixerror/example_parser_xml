package ua.radchenko.xml.dom.builder;



public class DomBuilderTest {
	public static void main(String[] args) {
		AccauntsDOMBuilder domBuilder = new AccauntsDOMBuilder();
		domBuilder.buildSetAccount("data_sax_validator/accounts.xml");
		System.out.println(domBuilder.getAccounts());
	}
}
