package ua.radchenko.xml.sax.builder;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import ua.radchenko.xml.bean.Account;


public class AccountHandler extends DefaultHandler {
	private Set<Account> accounts;
	private Account current = null;
	private AccountEnum currentEnum = null;
	private EnumSet<AccountEnum> withText;

	public AccountHandler() {
		accounts = new HashSet<Account>();
		withText = EnumSet.range(AccountEnum.NAME, AccountEnum.STREET);
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		if ("account".equals(localName)) {
			current = new Account();
			current.setLogin(attrs.getValue(0));
			if (attrs.getLength() == 2) {
				current.setEmail(attrs.getValue(1));
			}
		} else {
			AccountEnum temp = AccountEnum.valueOf(localName.toUpperCase());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if ("account".equals(localName)) {
			accounts.add(current);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case NAME:
				current.setName(s);
				break;
			case TELEPHONE:
				current.setTelephone(Integer.parseInt(s));
				break;
			case STREET:
				current.getAddress().setStreet(s);
				break;
			case CITY:
				current.getAddress().setCity(s);
				break;
			case COUNTRY:
				current.getAddress().setCountry(s);
				break;
			default:
				throw new EnumConstantNotPresentException(
						currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}
}
