package ua.radchenko.xml.jaxb.unmarshal;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import ua.radchenko.xml.bean.Accounts;


public class UnMarshalTest {
	public static void main(String[] args) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Accounts.class);
			Unmarshaller u = jc.createUnmarshaller();
			FileReader reader = new FileReader("data_marshal/accounts_marsh.xml");
			Accounts students = (Accounts) u.unmarshal(reader);
			System.out.println(students);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
