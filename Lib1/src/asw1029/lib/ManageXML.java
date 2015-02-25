package asw1029.lib;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Class to create, transform and pars a xml document
 */
public class ManageXML {

    private final Transformer transformer;
    private final DocumentBuilder builder;

    /**
     *
     * @throws TransformerConfigurationException
     * @throws ParserConfigurationException
     */
    public ManageXML() throws TransformerConfigurationException, ParserConfigurationException {
        transformer = TransformerFactory.newInstance().newTransformer();
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /**
     *
     * @param rootTag
     * @return New xml file
     */
    public Document newDocument(String rootTag) {
        Document newDoc = builder.newDocument();
        newDoc.appendChild(newDoc.createElement(rootTag));
        return newDoc;
    }

    /**
     *
     * @param out
     * @param document
     * @throws TransformerException
     * @throws IOException
     */
    public void transform(OutputStream out, Document document) throws TransformerException, IOException {
        transformer.transform(new DOMSource(document), new StreamResult(out));
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public Document parse(InputStream in) throws IOException, SAXException {
        return builder.parse(in);
    }
}
