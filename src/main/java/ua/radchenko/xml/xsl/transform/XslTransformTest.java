package ua.radchenko.xml.xsl.transform;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslTransformTest {
	public static void main(String[ ] args) {
	      try {
	         TransformerFactory tf = TransformerFactory.newInstance();
	         // установка используемого XSL-преобразования
	         Transformer transformer = tf.newTransformer(new StreamSource("data_transform/accounts.xsl"));
	         // установка исходного XML-документа и конечного XML-файла
	         transformer.transform(new StreamSource("data_sax_validator/accounts.xml"), new StreamResult("data_transform/accounts_new.xml"));
	         System.out.println("Transform " + "fileName" + " complete");
	         } catch(TransformerException e) {
	               System.err.println("Impossible transform file " + "fileName" + " : " + "ex");
	         }
	     }
}
