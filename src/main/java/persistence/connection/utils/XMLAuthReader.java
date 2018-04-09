package persistence.connection.utils;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLAuthReader {

	private static XMLAuthReader INSTANCE;
	private XMLAuthReader() {}
	public static XMLAuthReader getInstance() {
		if(INSTANCE == null)
			INSTANCE = new XMLAuthReader();
		return INSTANCE;
	}
	
	/**
	 * get data association from atributes at nodeName from filename xml data file
	 * @param filename
	 * @param nodeName the node from where we want attributes values
	 * @param attributes attributes needed
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public Map<String,String> getXMLData(String filename, String nodeName, String[] attributes) throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(filename));
		Element docElement = doc.getDocumentElement();
		Map<String, String> res = new HashMap<String, String>();
		NodeList nl =  docElement.getElementsByTagName(nodeName);
		if(nl != null) {
			Node node = nl.item(0);
			for(String attr : attributes) {
				NamedNodeMap attrs = node.getAttributes();
				if(attrs != null) {
					Node attrsNode = attrs.getNamedItem(attr);
					if(attrsNode != null)
						res.put(attr, attrsNode.getNodeValue());
				}
			}
		}
		return res;
	}
}
