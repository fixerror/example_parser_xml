package ua.radchenko.xml.create_xml;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateDocumentXML {
	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = documentBuilder.newDocument();
		String root = "product";
		Element rootElement = document.createElement(root);
		document.appendChild(rootElement);
		for (int i = 0; i < 1; i++) {
			String elementName = "name";
			Element emName = document.createElement(elementName);
			String name = "TV";
			emName.appendChild(document.createTextNode(name));
			String elementNameProduct = "name_product";
			Element emProduct = document.createElement(elementNameProduct);
			String productName = "Samssssung";
			emProduct.appendChild(document.createTextNode(productName));
			emProduct.setAttribute("id", "3");
			rootElement.appendChild(emName);
			rootElement.appendChild(emProduct);
		}
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileWriter(
					"create_data/product.xml"));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
