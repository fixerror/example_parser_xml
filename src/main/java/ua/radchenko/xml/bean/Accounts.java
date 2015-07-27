package ua.radchenko.xml.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Accounts {
	@XmlElement(name = "account")
	private ArrayList<Account> list = new ArrayList<Account>();

	public Accounts() {
		super();
	}

	public void setList(ArrayList<Account> list) {
		this.list = list;
	}

	public boolean add(Account st) {
		return list.add(st);
	}

	@Override
	public String toString() {
		return "Account [list=" + list + "]";
	}
}
