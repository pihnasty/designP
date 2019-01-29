package tests.pdfTest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * Created by Pihnastyi.O on 1/12/2017.
 */
public class _01_HelloWorld {
    public static void main(String[] arg) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );

// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.TIMES_BOLD;

// Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.moveTextPositionByAmount( 500, 700 );
        contentStream.drawString( "Hello World" );
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();

// Save the results and ensure that the document is properly closed:
        document.save( "Hello World.pdf");
        contentStream.close();


    }

}
