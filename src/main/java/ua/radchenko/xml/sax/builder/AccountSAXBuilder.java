package ua.radchenko.xml.sax.builder;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ua.radchenko.xml.bean.Account;


public class AccountSAXBuilder {
 private Set<Account> acounts;
 private AccountHandler sh;
 private XMLReader reader;
 public AccountSAXBuilder() {
  // создание SAX-анализатора
  sh = new AccountHandler();
  try {
   // создание объекта-обработчика
   reader = XMLReaderFactory.createXMLReader();
   reader.setContentHandler(sh);
  } catch (SAXException e) {
   System.err.print("ошибка SAX парсера: " + e);
  }
 }
 public Set<Account> getAccounts() {
  return acounts;
 }
 public void buildSetAccounts(String fileName) {
  try {
   // разбор XML-документа
   reader.parse(fileName);
  } catch (SAXException e) {
   System.err.print("ошибка SAX парсера: " + e);
  } catch (IOException e) {
   System.err.print("ошибка I/О потока: " + e);
  }
  acounts = sh.getAccounts();
 }

}
