package ua.radchenko.xml.stax.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ua.radchenko.xml.bean.Account;



public class AccountsStAXBuilder {
	private HashSet<Account> accounts = new HashSet<Account>();
	private XMLInputFactory inputFactory;

	public AccountsStAXBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void buildSetAccounts(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			// StAX parsing
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (AccountEnum.valueOf(name.toUpperCase()) == AccountEnum.ACCOUNT) {
						Account st = buildStudent(reader);
						accounts.add(st);
					}
				}
			}
		} catch (XMLStreamException ex) {
			System.err.println("StAX parsing error! " + ex.getMessage());
		} catch (FileNotFoundException ex) {
			System.err.println("File " + fileName + " not found! " + ex);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.err.println("Impossible close file " + fileName + " : "
						+ e);
			}
		}

	}

	@SuppressWarnings("incomplete-switch")
	private Account buildStudent(XMLStreamReader reader)
			throws XMLStreamException {
		Account st = new Account();
		st.setLogin(reader.getAttributeValue(null, AccountEnum.LOGIN.getValue()));
		st.setEmail(reader.getAttributeValue(null,
				AccountEnum.EMAIL.getValue())); // проверить на null
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (AccountEnum.valueOf(name.toUpperCase())) {
				case NAME:
					st.setName(getXMLText(reader));
					break;
				case TELEPHONE:
					name = getXMLText(reader);
					st.setTelephone(Integer.parseInt(name));
					break;
				case ADDRESS:
					st.setAddress(getXMLAddress(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (AccountEnum.valueOf(name.toUpperCase()) == AccountEnum.ACCOUNT) {
					return st;
				}
				break;
			}

		}
		throw new XMLStreamException("Unknown element in tag Student");
	}

	@SuppressWarnings("incomplete-switch")
	private Account.Address getXMLAddress(XMLStreamReader reader)
			throws XMLStreamException {
		Account.Address address = new Account.Address();
		int type;
		String name;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (AccountEnum.valueOf(name.toUpperCase())) {
				case COUNTRY:
					address.setCountry(getXMLText(reader));
					break;
				case CITY:
					address.setCity(getXMLText(reader));
					break;
				case STREET:
					address.setStreet(getXMLText(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (AccountEnum.valueOf(name.toUpperCase()) == AccountEnum.ADDRESS) {
					return address;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Address");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}