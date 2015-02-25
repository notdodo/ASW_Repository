package asw1029.servlet;

import asw1029.lib.ManageXML;
import asw1029.utils.ManageDB;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.transform.TransformerException;
import org.w3c.dom.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.SAXException;

/**
 * Servlet to manage all the request.
 */
@WebServlet(urlPatterns = {"/service"}, asyncSupported = true)
public class Service extends HttpServlet {

    HashMap<String, Object> users = new HashMap<>();
    HashMap<String, Object> interests = new HashMap<>();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submitPost") != null) {
            this.submitForm(request, response);
        } else {
            InputStream is = request.getInputStream();
            response.setContentType("text/xml;charset=UTF-8");
            try {
                ManageXML mngXML = new ManageXML();
                Document data = mngXML.parse(is);
                is.close();
                operations(data, request, response, mngXML);
            } catch (TransformerConfigurationException | ParserConfigurationException | IOException | SAXException e) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * Parse the XML sended from the client and execute the requested service.
     *
     * @param data XML Document
     * @param request HttpServletRequest for the servlet
     * @param response HttpServletRequest from the servlet
     * @param mngXML Object of the library ManageXML
     */
    private void operations(Document data, HttpServletRequest request, HttpServletResponse response, ManageXML mngXML) {
        Element root = data.getDocumentElement();
        String operation = root.getTagName();
        Document answer = null;
        OutputStream os;
        String realPath = getServletContext().getRealPath("");
        ManageDB mngdb = new ManageDB(realPath);
        ManageXML mngX = null;
        HttpSession session = request.getSession();
        HashMap<String, String> infoUser;
        HashMap<String, String> info;
        try {
            mngX = new ManageXML();
        } catch (TransformerConfigurationException | ParserConfigurationException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, "[service]: error on operations", ex);
        }
        switch (operation) {
            case "showCars":
                try {
                    request.setAttribute("utente", mngdb.getCarsInfo("utente"));
                    request.setAttribute("marca", mngdb.getCarsInfo("marca"));
                    request.setAttribute("modello", mngdb.getCarsInfo("modello"));
                    request.setAttribute("img", mngdb.getCarsInfo("img"));
                    request.setAttribute("prezzo", mngdb.getCarsInfo("prezzo"));
                    request.getRequestDispatcher("WEB-INF/showroom.jsp").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "showUser":
                String user = data.getElementsByTagName("showUser").item(0).getTextContent();
                try {
                    infoUser = mngdb.getUserInfo(user);
                    request.setAttribute("infoUser", infoUser);
                    request.getRequestDispatcher("WEB-INF/infouser.jsp").forward(request, response);
                } catch (Exception e) {
                    try {
                        request.getRequestDispatcher("WEB-INF/notfound.jsp").forward(request, response);
                    } catch (ServletException | IOException ex1) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                break;
            case "auction":
                String car = data.getElementsByTagName("nome").item(0).getTextContent();
                try {
                    info = mngdb.getCarInfo(car);
                    request.setAttribute("info", info);
                    request.getRequestDispatcher("WEB-INF/showcar.jsp").forward(request, response);
                } catch (Exception e) {
                    try {
                        request.getRequestDispatcher("WEB-INF/notfound.jsp").forward(request, response);
                    } catch (ServletException | IOException ex1) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                break;
            case "newCar":
                if (mngdb.addCar(data)) {
                    answer = mngXML.newDocument("ok");
                } else {
                    answer = mngXML.newDocument("error");
                }
                try {
                    os = response.getOutputStream();
                    mngX.transform(os, answer);
                    os.close();
                } catch (IOException | TransformerException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "startAuction":
                String userAuc = (String) session.getAttribute("user");
                String carAuc = data.getDocumentElement().getChildNodes().item(0).getTextContent();
                synchronized (this) {
                    // Elimino eventuali notifiche precedenti
                    Object obj = this.users.get(userAuc);
                    if (obj instanceof AsyncContext) {
                        ((AsyncContext) obj).complete();
                    }
                    this.interests.put(userAuc, carAuc);
                    this.users.put(userAuc, new LinkedList<Document>());
                    System.out.println("Utente: " + userAuc + " è interessato a: " + this.interests.get(userAuc));
                    String prezzo;
                    try {
                        prezzo = mngdb.getCarInfo(carAuc).get("prezzo");
                        answer = mngX.newDocument("ok");
                        Node prezzoEle = answer.createElement("prezzo").appendChild(answer.createTextNode(prezzo));
                        answer.getDocumentElement().appendChild(prezzoEle);
                    } catch (Exception ex) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    os = response.getOutputStream();
                    mngX.transform(os, answer);
                    os.close();
                } catch (IOException | TransformerException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "newOffer":
                String carOfferName = data.getElementsByTagName("name").item(0).getTextContent();
                String offer = data.getElementsByTagName("offer").item(0).getTextContent();
                user = (String) session.getAttribute("user");
                try {
                    info = mngdb.getCarInfo(carOfferName);
                    if (Integer.parseInt(info.get("prezzo")) >= Integer.parseInt(offer)) {
                        answer = mngX.newDocument("error");
                    } else {
                        mngdb.newCarPrice(carOfferName, offer, user);
                        // Aggiungo notifica
                        synchronized (this) {
                            for (String destUser : this.users.keySet()) {
                                String userInterest = (String) this.interests.get(destUser);
                                if (userInterest.equals(carOfferName)) {
                                    Object value = this.users.get(destUser);
                                    if (value instanceof AsyncContext) {
                                        OutputStream aos = ((AsyncContext) value).getResponse().getOutputStream();
                                        mngXML.transform(aos, data);
                                        aos.close();
                                        ((AsyncContext) value).complete();
                                        this.users.put(destUser, new LinkedList<Document>());
                                    } else {
                                        ((LinkedList<Document>) value).addLast(data);
                                    }
                                }
                            }
                        }
                        answer = mngX.newDocument("ok");
                    }
                    os = response.getOutputStream();
                    mngXML.transform(os, answer);
                    os.close();
                } catch (Exception ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "getNotification":
                String curUser = (String) session.getAttribute("user");
                boolean async;
                synchronized (this) {
                    if (!(this.users.get(curUser) instanceof LinkedList)) {
                        return;
                    }
                    LinkedList<Document> list = (LinkedList<Document>) this.users.get(curUser);
                    if (async = list.isEmpty()) {
                        AsyncContext asyncContext = request.startAsync();
                        asyncContext.setTimeout(1200 * 1000);
                        asyncContext.addListener(new AsyncAdapter() {
                            @Override
                            public void onTimeout(AsyncEvent e) {
                                try {
                                    ManageXML mngXML = new ManageXML();
                                    AsyncContext asyncContext = e.getAsyncContext();
                                    HttpServletRequest reqAsync = (HttpServletRequest) asyncContext.getRequest();
                                    String user = (String) reqAsync.getSession().getAttribute("user");
                                    Document answer = mngXML.newDocument("timeout");
                                    boolean confirm;
                                    synchronized (Service.this) {
                                        if (confirm = (users.get(user) instanceof AsyncContext)) {
                                            users.put(user, new LinkedList<Document>());
                                        }
                                    }
                                    if (confirm) {
                                        try (OutputStream tos = asyncContext.getResponse().getOutputStream()) {
                                            mngXML.transform(tos, answer);
                                        }
                                        asyncContext.complete();
                                    }
                                } catch (ParserConfigurationException | IOException | TransformerException ex) {
                                    System.out.println("AsyncContext Time Expired");
                                }
                            }
                        });
                        this.users.put(curUser, asyncContext);
                    } else {
                        answer = list.removeFirst();
                    }
                }
                if (!async) {
                    try {
                        os = response.getOutputStream();
                        mngX.transform(os, answer);
                        os.close();
                    } catch (IOException | TransformerException ex) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "recentCars":
                try {
                    HashMap<Date, String> date = mngdb.recentCars();
                    Map<Date, String> sortedMap = new TreeMap<>(date);
                    request.setAttribute("date", sortedMap);
                    request.getRequestDispatcher("WEB-INF/showrecent.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "sellCar":
                carOfferName = data.getElementsByTagName("nome").item(0).getTextContent();
                try {
                    info = mngdb.getCarInfo(carOfferName);
                    String userBuyer = info.get("ultimaOfferta");
                    request.setAttribute("info", info);
                    request.setAttribute("buyer", userBuyer);
                    mngdb.moveCar(carOfferName);
                    request.getRequestDispatcher("WEB-INF/sellcar.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                try {
                    request.getRequestDispatcher("WEB-INF/notfound.jsp").forward(request, response);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    /**
     * Receive the form submitted by POST
     *
     * @param request HttpServletRequest for the servlet
     * @param response HttpServletRequest from the servlet
     */
    private void submitForm(HttpServletRequest request, HttpServletResponse response) {
        String realPath = getServletContext().getRealPath("");
        ManageDB mngdb = new ManageDB(realPath);
        HttpSession session = request.getSession();
        try {
            ManageXML mngxml = new ManageXML();
        } catch (TransformerConfigurationException | ParserConfigurationException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (request.getParameter("submitPost")) {
            case "register":
                String nome = (String) request.getParameter("nome");
                String cognome = (String) request.getParameter("cognome");
                String nickname = (String) request.getParameter("nickname");
                String password = (String) request.getParameter("password1");
                String email = (String) request.getParameter("email");
                String address = (String) request.getParameter("indirizzo");
                mngdb.createXMLUser(nome, cognome, nickname, password, email, address);
                try {
                    response.sendRedirect("index.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "login":
                String user = (String) request.getParameter("user");
                String pass = (String) request.getParameter("password");
                try {
                    if (mngdb.checkLogin(user, pass)) {
                        session.setAttribute("user", user);
                        session.setAttribute("loginResult", "true");
                        session.setAttribute("logged", "true");
                        Cookie cookieLog = new Cookie("logged", "1");
                        Cookie cookieUse = new Cookie("user", user);
                        cookieLog.setMaxAge(60 * 60);
                        cookieUse.setMaxAge(60 * 60);
                        response.addCookie(cookieLog);
                        response.addCookie(cookieUse);
                        synchronized (this) {
                            this.users.put(user, new LinkedList<Document>());
                            this.interests.put(user, "");
                        }
                        response.sendRedirect("index.jsp");
                    } else {
                        session.setAttribute("loginResult", "false");
                        response.sendRedirect("index.jsp");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "logout":
                session.invalidate();
                for (Cookie cookie : request.getCookies()) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                try {
                    response.sendRedirect("index.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
