package ua.radchenko.xml.bean;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = { "name", "email", "telephone", "address" })
// задание последовательности элементов XML
public class Account {
	@XmlAttribute(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	private String login;
	@XmlElement(required = true)
	private String name;
	@XmlAttribute(required = false)
	private String email;
	@XmlElement(required = true)
	private int telephone;
	@XmlElement(required = true)
	private Address address = new Address();

	public Account() {
	} // необходим для маршаллизации/демаршалиизации XML

	public Account(String login, String name, String email, int telephone,
			Address address) {
		this.login = login;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.address = address;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return "\nlogin: " + login + "\nname: " + name + "\ntelephone: "
				+ telephone + "\nemail: " + email + address.toString();
	}

	@XmlRootElement
	@XmlType(name = " address ", propOrder = { "city", "country", "street" })
	public static class Address { // внутренний класс
		private String country;
		private String city;
		private String street;

		public Address() {// необходим для маршаллизации/демаршалиизации XML
		}

		public Address(String country, String city, String street) {
			this.country = country;
			this.city = city;
			this.street = street;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String toString() {
			return "\naddress:" + "\n\tcountry: " + country + "\n\tcity: "
					+ city + "\n\tstreet: " + street + "\n";
		}
	}
}
