package ua.radchenko.xml.jaxb.marshal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ua.radchenko.xml.bean.Account;
import ua.radchenko.xml.bean.Accounts;

public class MarshalTestJAXB {
	public static void main(String[] args) {
		try {
			JAXBContext context = JAXBContext.newInstance(Accounts.class);
			Marshaller m = context.createMarshaller();
			Accounts accounts = new Accounts() {
				{
					Account.Address addr = new Account.Address("UA", "Kharkiv",
							"Strit 1");
					Account account = new Account("fixerror1", "Aleksandr",
							"ra@gmail.com", 12345, addr);
					this.add(account);
					addr = new Account.Address("UA", "Kharkiv",
							"Strit 2");
					account = new Account("fixerror2", "Aleksandr",
							"ra@gmail.com", 54321, addr);
					this.add(account);
				}
			};
			m.marshal(accounts, new FileOutputStream("data_marshal/accounts_marsh.xml"));
			m.marshal(accounts, System.out); // копия на консоль
			System.out.println("XML-файл создан");
		} catch (FileNotFoundException e) {
			System.out.println("XML-файл не может быть создан: " + e);
		} catch (JAXBException e) {
			System.out.println("JAXB-контекст ошибочен " + e);
		}
	}
}
