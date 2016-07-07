package testrecorddl;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pihnastyi.O on 7/5/2016.
 */
public class XmlRW {
    public static List<String> readCommands(String path) {
        List<String> commands = new ArrayList();
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = builder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element elementChild = (Element) child;
                Text textNode = (Text) elementChild.getFirstChild();
                commands.add(textNode.getData().trim());
            }
        }
        return commands;
    }

    public static void writeCommands(String path, List<String> commands, String tagRootName, String tagChildName) {
        if (commands.isEmpty() == false) {

            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = builder.newDocument();
            Transformer t = null;
            try {
                t = TransformerFactory.newInstance().newTransformer();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            Element root = doc.createElement(tagRootName);
            doc.appendChild(root);
            for (String command : commands) {
                Element child = doc.createElement(tagChildName);
                Text textNode = doc.createTextNode(command);
                child.appendChild(textNode);
                root.appendChild(child);
            }
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            try {
                t.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(file)));
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
