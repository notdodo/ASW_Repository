package asw1029.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import asw1029.lib.ManageXML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Class for manage the files
 */
public class ManageDB {

    private ManageXML mngx;
    private String context;

    /**
     * Class construnctor
     *
     * @param realPath Path of the directory "xml"
     */
    public ManageDB(String realPath) {
        try {
            this.mngx = new ManageXML();
            String path = "/WEB-INF/xml";
            synchronized (this) {
                File dir = new File(realPath + path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
            this.context = realPath + path;
        } catch (TransformerConfigurationException | ParserConfigurationException ex) {
            Logger.getLogger(ManageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add a new car.
     *
     * @param doc the file's content
     * @return true if the file is create
     */
    public boolean addCar(Document doc) {
        //creo file e directory
        boolean res = false;
        try {
            synchronized (this) {
                File dir = new File(this.context + "/auto");
                File dirVendute = new File(this.context + "/vendute");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                if (!dirVendute.exists()) {
                    dirVendute.mkdir();
                }
            }
            String marca = doc.getDocumentElement().getElementsByTagName("marca").item(0).getTextContent();
            String modello = doc.getDocumentElement().getElementsByTagName("modello").item(0).getTextContent();
            String utente = doc.getDocumentElement().getElementsByTagName("utente").item(0).getTextContent();
            String name = this.context + "/auto/" + utente + "." + marca + "." + modello + ".xml";
            synchronized (this) {
                File f = new File(name);
                res = f.createNewFile();
                //creo contenuto file
                this.mngx.transform(new FileOutputStream(f), doc);
            }
        } catch (IOException | TransformerException ex) {
            Logger.getLogger(ManageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    /**
     * Add a new user.
     *
     * @param doc the file's content
     * @return
     */
    public boolean addUser(Document doc) {
        //creo file e directory
        boolean res = false;
        try {
            synchronized (this) {
                File dir = new File(this.context + "/user");
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
            String nick = doc.getDocumentElement().getElementsByTagName("nickname").item(0).getTextContent();
            String nameFile = this.context + "/user/" + nick + ".xml";
            synchronized (this) {
                File f = new File(nameFile);
                res = f.createNewFile();
                //creo contenuto file
                this.mngx.transform(new FileOutputStream(f), doc);
            }
        } catch (IOException | TransformerException ex) {
            Logger.getLogger(ManageDB.class.getName()).log(Level.SEVERE, "TransFormer:" + ManageDB.class.getName(), ex);
        }
        return res;
    }

    /**
     * Get the information of all the cars.
     * 
     * @param tag The tag to search
     * @return The information for one tag for all the cars
     * @throws Exception
     */
    public String[] getCarsInfo(String tag) throws Exception {
        String[] result = null;
        synchronized (this) {
            File dir = new File(this.context + "/auto");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            result = new String[dir.listFiles().length];
            int i = 0;
            for (File f : dir.listFiles()) {
                Document doc = this.mngx.parse(new FileInputStream(f));
                Element root = doc.getDocumentElement();
                String info = root.getElementsByTagName(tag).item(0).getTextContent();
                result[i] = info;
                i++;
            }
        }
        return result;
    }

    /**
     * Get the information of a single car
     *
     * @param name Name of the file xml of the car
     * @return Car's info
     * @throws Exception
     */
    public HashMap<String, String> getCarInfo(String name) throws Exception {
        HashMap<String, String> info = new HashMap<>();
        synchronized (this) {
            File dir = new File(this.context + "/auto");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            for (File f : dir.listFiles()) {
                if (f.getName().equals(name + ".xml")) {
                    Document doc = this.mngx.parse(new FileInputStream(f));
                    Element root = doc.getDocumentElement();
                    info.put("utente", root.getElementsByTagName("utente").item(0).getTextContent());
                    info.put("marca", root.getElementsByTagName("marca").item(0).getTextContent());
                    info.put("modello", root.getElementsByTagName("modello").item(0).getTextContent());
                    info.put("km", root.getElementsByTagName("km").item(0).getTextContent());
                    info.put("anno", root.getElementsByTagName("anno").item(0).getTextContent());
                    info.put("carburante", root.getElementsByTagName("carburante").item(0).getTextContent());
                    info.put("potenza", root.getElementsByTagName("cilindrata").item(0).getTextContent());
                    info.put("prezzo", root.getElementsByTagName("prezzo").item(0).getTextContent());
                    info.put("note", root.getElementsByTagName("note").item(0).getTextContent());
                    info.put("ultimaOfferta", root.getElementsByTagName("ultimaOfferta").item(0).getTextContent());
                    info.put("img", root.getElementsByTagName("img").item(0).getTextContent());
                    return info;
                }
            }
        }
        throw new Exception("[ManageDB]: Macchina non trovata");
    }

    /**
     * Get the information of a user.
     *
     * @param name Nickname of the user
     * @return The information of the user
     * @throws Exception
     */
    public HashMap<String, String> getUserInfo(String name) throws Exception {
        HashMap<String, String> info = new HashMap<>();
        synchronized (this) {
            File dir = new File(this.context + "/user");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            for (File f : dir.listFiles()) {
                Document doc = this.mngx.parse(new FileInputStream(f));
                Element root = doc.getDocumentElement();
                if (root.getElementsByTagName("nickname").item(0).getTextContent().equals(name)) {
                    info.put("nome", root.getElementsByTagName("nome").item(0).getTextContent());
                    info.put("cognome", root.getElementsByTagName("cognome").item(0).getTextContent());
                    info.put("nickname", root.getElementsByTagName("nickname").item(0).getTextContent());
                    info.put("email", root.getElementsByTagName("email").item(0).getTextContent());
                    info.put("indirizzo", root.getElementsByTagName("indirizzo").item(0).getTextContent());
                    return info;
                }
            }
        }
        throw new Exception("[ManageDB]: Utente non trovato");
    }

    /**
     * Change the price of a car after a new offer.
     *
     * @param name Name of the file xml of the car
     * @param price New price of the car
     * @param user User who made the offer
     * @throws Exception
     */
    public void newCarPrice(String name, String price, String user) throws Exception {
        synchronized (this) {
            File dir = new File(this.context + "/auto");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            for (File f : dir.listFiles()) {
                if (f.getName().equals(name + ".xml")) {
                    Document doc = this.mngx.parse(new FileInputStream(f));
                    Element node = (Element) doc.getElementsByTagName("prezzo").item(0);
                    Text Oldtext = (Text) node.getChildNodes().item(0);
                    Text newText = doc.createTextNode(price);
                    node.replaceChild(newText, Oldtext);
                    Element nodeUser = (Element) doc.getElementsByTagName("ultimaOfferta").item(0);
                    Text OldUser = (Text) nodeUser.getChildNodes().item(0);
                    Text newUser = doc.createTextNode(user);
                    nodeUser.replaceChild(newUser, OldUser);
                    this.mngx.transform(new FileOutputStream(f), doc);
                    return;
                }
            }
        }
        throw new Exception("[ManageDB]: Utente non trovato");
    }

    /**
     * Create the XML file for the registred user
     * 
     * @param nome User's name
     * @param cognome User's surname
     * @param nickname User's nickname
     * @param password User's password
     * @param email User's mail
     * @param indirizzo User's address
     * @return A xml document
     */
    public Document createXMLUser(String nome, String cognome, String nickname, String password, String email, String indirizzo) {
        Document doc = this.mngx.newDocument("user");
        Element rootElement = doc.getDocumentElement();

        Element nomeR = doc.createElement("nome");
        nomeR.appendChild(doc.createTextNode(nome));
        rootElement.appendChild(nomeR);

        Element cognomeR = doc.createElement("cognome");
        cognomeR.appendChild(doc.createTextNode(cognome));
        rootElement.appendChild(cognomeR);

        Element nicknameR = doc.createElement("nickname");
        nicknameR.appendChild(doc.createTextNode(nickname));
        rootElement.appendChild(nicknameR);

        Element passwordR = doc.createElement("password");
        passwordR.appendChild(doc.createTextNode(password));
        rootElement.appendChild(passwordR);

        Element emailR = doc.createElement("email");
        emailR.appendChild(doc.createTextNode(email));
        rootElement.appendChild(emailR);

        Element indirizzoR = doc.createElement("indirizzo");
        indirizzoR.appendChild(doc.createTextNode(indirizzo));
        rootElement.appendChild(indirizzoR);
        this.addUser(doc);
        return doc;
    }

    /**
     * Check the credential of the user.
     *
     * @param user User to login
     * @param password Password related to the user
     * @return True if "user" and "password" are correct, false otherwise
     * @throws Exception
     */
    public boolean checkLogin(String user, String password) throws Exception {
        synchronized (this) {
            File dir = new File(this.context + "/user");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            for (File f : dir.listFiles()) {
                Document doc = this.mngx.parse(new FileInputStream(f));
                Element root = doc.getDocumentElement();
                String nick = root.getElementsByTagName("nickname").item(0).getTextContent();
                String pass = root.getElementsByTagName("password").item(0).getTextContent();
                if (nick.equals(user) && pass.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return an ordered map by date of the cars.
     * 
     * @return HashMap of the cars ordered by date
     * @throws Exception
     */
    public HashMap<Date, String> recentCars() throws Exception {
        HashMap<Date, String> result = new HashMap<>();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String name;
        synchronized (this) {
            File dir = new File(this.context + "/auto");
            if (!dir.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            for (File f : dir.listFiles()) {
                name = f.getName().substring(0, f.getName().lastIndexOf('.'));
                Document doc = this.mngx.parse(new FileInputStream(f));
                Element root = doc.getDocumentElement();
                String data = root.getElementsByTagName("data").item(0).getTextContent();
                result.put(ft.parse(data), name);
            }
        }
        return result;
    }

    /**
     *
     * @param name Move a sold car
     * @throws Exception
     */
    public void moveCar(String name) throws Exception {
        synchronized (this) {
            File dirSou = new File(this.context + "/auto");
            File dirDes = new File(this.context + "/vendute");
            if (!dirSou.exists() || !dirDes.exists()) {
                throw new Exception("[ManageDB]:no directory");
            }
            File source = new File(this.context + "/auto/" + name + ".xml");
            File destin = new File(this.context + "/vendute/" + name + ".xml");
            Files.move(source.toPath(), destin.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
