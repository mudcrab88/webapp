package ru.kuznetsovnn.webapp;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Contract {

    private File fileContract;
    private String[] tagNames = {"purchaseID", "RID", "number", "object", "price"};

    Contract(File file) {
        fileContract = file;
        parseFile();
    }

    public void parseFile() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(fileContract.getAbsolutePath());
            for (String tagName : tagNames) {
                NodeList nodeList = document.getElementsByTagName(tagName);
                if ( nodeList.getLength() > 0) {
                    String nodeText = new String(nodeList.item(0).getTextContent().getBytes(), "cp1251");
                    String nodeName = nodeList.item(0).getNodeName();
                    System.out.println(nodeName + ": " + nodeText);
                    System.out.println(document.getInputEncoding());
                }
            }
        }
        catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        }
        catch (SAXException ex) {
            ex.printStackTrace(System.out);
        }
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}