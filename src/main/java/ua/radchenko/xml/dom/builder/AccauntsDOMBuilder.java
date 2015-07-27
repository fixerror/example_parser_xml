package ua.radchenko.xml.dom.builder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.radchenko.xml.bean.Account;


public class AccauntsDOMBuilder {
	private Set<Account> accaunts;
	private DocumentBuilder docBuilder;

	public AccauntsDOMBuilder() {
		this.accaunts = new HashSet<Account>();
		// создание DOM-анализатора
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Ошибка конфигурации парсера: " + e);
		}
	}

	public Set<Account> getAccounts() {
		return accaunts;
	}

	public void buildSetAccount(String fileName) {
		Document doc = null;
		try {
			// parsing XML-документа и создание древовидной структуры
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			// получение списка дочерних элементов <student>
			NodeList accountsList = root.getElementsByTagName("account");
			for (int i = 0; i < accountsList.getLength(); i++) {
				Element accountElement = (Element) accountsList.item(i);
				Account account = buildAccount(accountElement);
				accaunts.add(account);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private Account buildAccount(Element accountElement) {
		Account account = new Account();
		// заполнение объекта student
		account.setEmail(accountElement.getAttribute("email")); // проверка
																	// на null
		account.setName(getElementTextContent(accountElement, "name"));
		Integer tel = Integer.parseInt(getElementTextContent(accountElement,
				"telephone"));
		account.setTelephone(tel);
		Account.Address address = account.getAddress();
		// заполнение объекта address
		Element adressElement = (Element) accountElement.getElementsByTagName(
				"address").item(0);
		address.setCountry(getElementTextContent(adressElement, "country"));
		address.setCity(getElementTextContent(adressElement, "city"));
		address.setStreet(getElementTextContent(adressElement, "street"));
		account.setLogin(accountElement.getAttribute("login"));
		return account;
	}

	// получение текстового содержимого тега
	private static String getElementTextContent(Element element,
			String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
