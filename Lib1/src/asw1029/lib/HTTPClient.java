package asw1029.lib;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 */
public class HTTPClient {

    private URL base = null;
    private String sessionId = null;

    /**
     *
     * @return
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @param base
     */
    public void setBase(URL base) {
        this.base = base;
    }

    /**
     *
     * @return
     */
    public URL getBase() {
        return base;
    }

    /**
     *
     * @param address
     * @param data
     * @return
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws MalformedURLException
     */
    public Document execute(String address, Document data) throws TransformerException, ParserConfigurationException, SAXException, IOException, MalformedURLException {
        ManageXML manageXML = new ManageXML();

        HttpURLConnection connection = (HttpURLConnection) new URL(base, address).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        if (sessionId != null) {
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionId);
        }
        connection.setRequestProperty("Accept", "text/xml");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.connect();

        try (OutputStream out = connection.getOutputStream()) {
            manageXML.transform(out, data);
        }

        Document answer;
        try (InputStream in = connection.getInputStream()) {
            answer = manageXML.parse(in);
        }

        String setCookie = connection.getHeaderField("Set-Cookie");
        if (setCookie != null && !setCookie.equals("") && (setCookie.substring(0, setCookie.indexOf("=")).equals("JSESSIONID"))) {
            sessionId = setCookie.substring(setCookie.indexOf("=") + 1, setCookie.indexOf(";"));
        }

        connection.disconnect();
        return answer;
    }
}
