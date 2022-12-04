package by.issoft.config;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class XMLParser {

    public static LinkedHashMap<String, SortDirection> getSortConfig() {
        LinkedHashMap<String, SortDirection> result = new LinkedHashMap<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("config/src/main/resources/config.xml"));
            document.normalize();
            NodeList nList = document.getElementsByTagName("sort").item(0).getChildNodes();
            for (int i = 0; i < nList.getLength(); i++) {
                if (nList.item(i).getNodeType() == 1) {
                    try {
                        SortDirection sortDirection = SortDirection.valueOf(nList.item(i).getTextContent().toUpperCase());
                        result.put(nList.item(i).getNodeName(), sortDirection);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
