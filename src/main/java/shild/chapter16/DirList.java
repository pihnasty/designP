package shild.chapter16;
//  listing 11
//  Display a directory. Requires JDK 7 or later.

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DirList {
    public static void main(String args[]) {
        String dirname = "E:\\A\\ETL\\SSIS-Glue F\\SSIS-Glue";
        String glob = "*.dtsx";

        // Obtain and manage a directory stream within an ARM Block.
        getFileNames(dirname, glob);





    }

    private static void original() {
        String fileName ="qwqwqw";
        Document dbmsXml;
        InputStream resFile = DirList.class.getResourceAsStream(fileName);


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            dbmsXml = documentBuilder.parse(resFile);
            dbmsXml.getDocumentElement().normalize();




        } catch (Exception e) {

        }
    }

    private static void original2(File file ) {

        Document dbmsXml;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            dbmsXml = documentBuilder.parse(file);
            dbmsXml.getDocumentElement().normalize();
            System.out.println();

            XPath xpath = XPathFactory.newInstance().newXPath();

            Node packageNode = dbmsXml.getFirstChild();
            Node node = (Node) xpath.evaluate("//Executable", packageNode , XPathConstants.NODE);
            System.out.println();

        } catch (Exception e) {    // DTS:Executable

        }
    }

    private static void getFileNames(String dirname, String glob) {

        try ( DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname),glob) ) {
            for(Path entry : dirstrm) {
                System.out.println(entry.getFileName());

                original2( entry.toFile() );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}