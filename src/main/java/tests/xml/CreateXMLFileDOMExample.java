package tests.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateXMLFileDOMExample {

    private static final String FILENAME = "staff.xml";

    public static void main(String[] args) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();


            // Корневой элемент
            Element company = document.createElement("company");
            document.appendChild(company);

            // Элемент типа staff
            Element staff = document.createElement("staff");
            company.appendChild(staff);

            // Определяем идентификатор сотрудника
            Attr id = document.createAttribute("id");
            id.setTextContent("1");
            staff.setAttributeNode(id);

            // Еще можно сделать так
            // staff.setAttribute("id", "1");

            // Определяем имя
            Element firstname = document.createElement("firstname");
            firstname.setTextContent("Иван");
            staff.appendChild(firstname);

            // Определяем фамилию
            Element lastname = document.createElement("lastname");
            lastname.setTextContent("Иванов");
            staff.appendChild(lastname);

            // Определяем никнейм
            Element nickname = document.createElement("nickname");
            nickname.setTextContent("ivanov");
            staff.appendChild(nickname);

            // Определяем зарплату
            Element salary = document.createElement("salary");
            salary.setTextContent("100000");
            staff.appendChild(salary);

            // Сохранить текстовое представление XML документа в файл
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(
                    new File("C:\\qqqqq\\p2.xml"));

            // Для соображений отладки можно вывести результат работы
            // программы на стандартный вывод
            // StreamResult result = new StreamResult(System.out);

            transformer.setOutputProperty(OutputKeys.INDENT,  "yes");
            transformer.setOutputProperty(OutputKeys.METHOD,  "xml");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount",  "2");


            transformer.transform(source, result);

            System.out.println("Документ сохранен!");

        } catch (ParserConfigurationException
                | TransformerConfigurationException ex) {
            Logger.getLogger(CreateXMLFileDOMExample.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CreateXMLFileDOMExample.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
