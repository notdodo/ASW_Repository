package asw.async;

import asw.imp.Applet1;
import java.awt.Component;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import asw1029.lib.HTTPClient;
import asw1029.lib.ManageXML;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * SwingWorker for Applet1's network operation
 */
public class SubmitListener extends SwingWorker<Object, Object> {

    private Applet1 context;
    private HTTPClient client;
    private ManageXML mngXML;
    private String modello, km, anno, carburante, cilindrata, baseAsta, descrizione, imgBase64, marca,
            utente, colore, numPorte, tipologia;

    /**
     * class costructor
     *
     * @param context
     * @param utente
     * @param descrizione
     * @param baseAsta
     * @param cilindrata
     * @param kmPercorsi
     * @param annoProduz
     * @param carburante
     * @param colore
     * @param marca
     * @param numPorte
     * @param tipologia
     * @param modello
     * @param imgBase64
     */
//    public SubmitListener(Applet1 c, String name, String km, String anno, String carburante, String potenza, String prezzo, String note, String img) {
    public SubmitListener(Applet1 context, String utente, String descrizione, String baseAsta, String cilindrata, String kmPercorsi,
            String annoProduz, String carburante, String colore, String marca, String numPorte, String tipologia, String modello, String imgBase64) {
        try {
            this.client = new HTTPClient();
            this.mngXML = new ManageXML();
            this.utente = utente;
            this.modello = modello;
            this.marca = marca;
            this.colore = colore;
            this.numPorte = numPorte;
            this.km = kmPercorsi;
            this.tipologia = tipologia;
            this.anno = annoProduz;
            this.carburante = carburante;
            this.cilindrata = cilindrata;
            this.baseAsta = baseAsta;
            this.descrizione = descrizione;
            this.context = context;
            this.imgBase64 = imgBase64;
        } catch (TransformerConfigurationException | ParserConfigurationException ex) {
            Logger.getLogger(SubmitListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Object doInBackground() throws Exception {
        System.out.println("[Swing Worker]: START!!");
        try {
            this.client.setBase(new URL("http://localhost:8080/WebApplication/service"));
            Document doc = createXML();
            Document answer = this.client.execute("service", doc);
            String nodeVal = answer.getDocumentElement().getTagName();
            this.publish(nodeVal);
            if (nodeVal.equals("ok")) {
                System.out.println("[Swing Worker]: operazione terminata con successo :)");
            }
        } catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void process(List<Object> chunks) {
        if (((String) chunks.get(0)).equals("ok")) {
            this.reWriteGUI(true);
        } else {
            this.reWriteGUI(false);
        }
    }

    /**
     * rewrite applet GUI after elaboration
     *
     * @param success must be true if the operation (in doBackground) was
     * correctly terminated
     */
    private void reWriteGUI(boolean success) {
        for (Component c : this.context.getContentPane().getComponents()) {
            this.context.getContentPane().remove(c);
            this.context.validate();
        }
        if (success) {
            this.context.getContentPane().add(new JPanel().add(new JLabel("La macchina è stata aggiunta al database")));
            this.context.validate();
        } else {
            this.context.getContentPane().add(new JPanel().add(new JLabel("errore, riprovare")));
            this.context.validate();
        }
    }

    /**
     * create the xml to send
     *
     * @return xml to send
     */
    private Document createXML() {
        Document doc = this.mngXML.newDocument("newCar");
        Element rootElement = doc.getDocumentElement();

        Element dataR = doc.createElement("data");
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String data = ft.format(dNow);
        dataR.appendChild(doc.createTextNode(data));
        rootElement.appendChild(dataR);        
        
        Element utenteR = doc.createElement("utente");
        utenteR.appendChild(doc.createTextNode(this.utente));
        rootElement.appendChild(utenteR);

        Element marcaR = doc.createElement("marca");
        marcaR.appendChild(doc.createTextNode(this.marca));
        rootElement.appendChild(marcaR);

        Element modelloR = doc.createElement("modello");
        modelloR.appendChild(doc.createTextNode(this.modello));
        rootElement.appendChild(modelloR);

        Element tipologiaR = doc.createElement("tipologia");
        tipologiaR.appendChild(doc.createTextNode(this.tipologia));
        rootElement.appendChild(tipologiaR);

        Element coloreR = doc.createElement("colore");
        coloreR.appendChild(doc.createTextNode(this.colore));
        rootElement.appendChild(coloreR);

        Element porteR = doc.createElement("porte");
        porteR.appendChild(doc.createTextNode(this.numPorte));
        rootElement.appendChild(porteR);

        Element carburanteR = doc.createElement("carburante");
        carburanteR.appendChild(doc.createTextNode(this.carburante));
        rootElement.appendChild(carburanteR);

        Element annoR = doc.createElement("anno");
        annoR.appendChild(doc.createTextNode(this.anno));
        rootElement.appendChild(annoR);

        Element cilindrataR = doc.createElement("cilindrata");
        cilindrataR.appendChild(doc.createTextNode(this.cilindrata));
        rootElement.appendChild(cilindrataR);

        Element kmR = doc.createElement("km");
        kmR.appendChild(doc.createTextNode(this.km));
        rootElement.appendChild(kmR);

        Element prezzoR = doc.createElement("prezzo");
        prezzoR.appendChild(doc.createTextNode(this.baseAsta));
        rootElement.appendChild(prezzoR);

        Element noteR = doc.createElement("note");
        noteR.appendChild(doc.createTextNode(this.descrizione));
        rootElement.appendChild(noteR);

        Element offertaR = doc.createElement("ultimaOfferta");
        offertaR.appendChild(doc.createTextNode("nullUser"));
        rootElement.appendChild(offertaR);

        Element imgR = doc.createElement("img");
        if ("".equals(this.imgBase64))
            this.imgBase64 = "noImage";
        imgR.appendChild(doc.createTextNode(this.imgBase64));
        rootElement.appendChild(imgR);

        return doc;
    }
}
