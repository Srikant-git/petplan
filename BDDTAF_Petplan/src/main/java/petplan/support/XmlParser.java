package petplan.support;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author Ashot Mkrtchyan
 */
public class XmlParser {
    private Document parseToXml(String xml) throws ParserConfigurationException, IOException, SAXException {
        return DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new org.xml.sax.InputSource(new StringReader(xml)));
    }

    public String getValue(String nodeName, String elemName, String xml) {
        try {
            Document doc = parseToXml(xml);
            NodeList nodes = doc.getElementsByTagName(nodeName);
            Element element = (Element) nodes.item(0);
            return element.getElementsByTagName(elemName)
                    .item(0)
                    .getTextContent();
        } catch (NullPointerException | ParserConfigurationException | IOException | SAXException e) {
            return null;
        }
    }

    /**
     * @autor Gevorg Mushyan
     * @since 03.09.2018
     */
    public String getValue(String elemName, String xml) {
        try {
            Document doc = parseToXml(xml);
            NodeList nodes = doc.getElementsByTagName(elemName);
            Element element = (Element) nodes.item(0);
            return element.getTextContent();
        } catch (NullPointerException | ParserConfigurationException | IOException | SAXException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "        <IPID>5646546544</IPID>\n";
        System.out.println((new XmlParser()).getValue( "IPID", xml));
    }
}
