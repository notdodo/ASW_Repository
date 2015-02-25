package asw.imp;

import java.io.File;
import asw.async.ImageLoader;
import asw.async.SubmitListener;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.jnlp.*;

/**
 * Applet to insert a car
 */
public class Applet1 extends javax.swing.JApplet {

    private File fileSelected;
    private String imgBase64;

    /**
     * Initializes the applet Applet1
     */
    @Override
    public void init() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applet1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initComponents();
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, "Error on applet", ex);
        }
    }

    /**
     * Rewrite applet GUI after ImageLoader's elaboration
     *
     * @param success must be true if the operation was correctly terminated
     * @param image
     */
    public void imageLoaded(final boolean success, String image) {
        if (success) {
            this.imgBase64 = image;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    Applet1.jTextFieldInserimentoImg.setText("Immagine: " + fileSelected.getName());
                } else {
                    Applet1.jTextFieldInserimentoImg.setText("errore");
                }
            }
        });
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLMarca = new javax.swing.JLabel();
        jLModello = new javax.swing.JLabel();
        jLColore = new javax.swing.JLabel();
        jLNumPorte = new javax.swing.JLabel();
        jLCarburante = new javax.swing.JLabel();
        jLAnnoProduz = new javax.swing.JLabel();
        jLBaseAsta = new javax.swing.JLabel();
        jLKmPercorsi = new javax.swing.JLabel();
        jComboBoxTipologia = new javax.swing.JComboBox();
        jComboBoxMarca = new javax.swing.JComboBox();
        jComboBoxColore = new javax.swing.JComboBox();
        jComboBoxNumPorte = new javax.swing.JComboBox();
        jComboBoxCarburante = new javax.swing.JComboBox();
        jComboBoxAnnoProduz = new javax.swing.JComboBox();
        jTextFieldModello = new javax.swing.JTextField();
        jLabelDescrizione = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescrizione = new javax.swing.JTextArea();
        jLTipologia = new javax.swing.JLabel();
        jLCilindrata = new javax.swing.JLabel();
        jTextFieldKmPercorsi = new javax.swing.JTextField();
        jTextFieldBaseAsta = new javax.swing.JTextField();
        jButtonInvioDati = new javax.swing.JButton();
        jTextFieldCilindrata = new javax.swing.JTextField();
        jButtonInserimentoImg = new javax.swing.JButton();
        jTextFieldInserimentoImg = new javax.swing.JTextField();

        jOptionPane1.setMessage("Errore di inserimento");
        jOptionPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        setMaximumSize(new java.awt.Dimension(400, 530));
        setMinimumSize(new java.awt.Dimension(400, 530));
        setPreferredSize(new java.awt.Dimension(400, 530));

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 530));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 530));

        jLabel1.setBackground(new java.awt.Color(242, 242, 242));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inserisci le info della tua auto");

        jLMarca.setText("Marca");

        jLModello.setText("Modello");

        jLColore.setText("Colore");

        jLNumPorte.setText("N. Porte");

        jLCarburante.setText("Carburante");

        jLAnnoProduz.setText("Anno Produzione");

        jLBaseAsta.setText("Base d'asta");

        jLKmPercorsi.setText("Km percorsi");

        jComboBoxTipologia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berlina", "Cabriolet", "City car", "Epoca", "Fuoristrada", "Monovolume", "Sportiva", "Station wagon", " " }));

        jComboBoxMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FIAT", "Abarth", "Acura", "Alfa Romeo", "Audi", "Bentley", "BMW", "Buick", "BYD", "Cadillac", "Chevrolet", "Citroën", "Dacia", "Daewoo", "Daihatsu", "Dodge", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Land Rover", "Lancia", "Nissan", "Opel", "Peugeut", "Renault", "Smart", "Toyota", "Volkswagen", "Volvo" }));

        jComboBoxColore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acquamarina", "Amaranto", "Ambra", "Ametista", "Arancione", "Arancione fiamma", "Ardesia", "Argento", "Asparago", "Avorio", "Azzurro", "Azzurro fiordaliso", "Azzurro Savoia", "Beige", "Beige-oliva chiaro", "Bianco", "Bianco antico", "Biscotto", "Bistro", "Blu", "Blu acciaio", "Blu alice", "Blu Bondi", "Blu Cadetto", "Blu ceruleo", "Blu di Persia", "Blu Dodger", "Blu elettrico", "Blu Klein", "Blu marino", "Blu oltremare", "Blu reale", "Bordeaux", "Bronzo", "Bronzo antico", "Cacca", "Camoscio", "Carbone", "Carminio", "Carta di zucchero", "Castagno", "Castagno scuro", "Castagno chiaro", "Catrame", "Catrame scuro", "Celadon", "Celeste", "Ceruleo", "Ceruleo scuro", "Chartreuse", "Ciano", "Ciliegia", "Cioccolato", "Conchiglia", "Corallo", "Crema", "Cremisi", "Denim", "Denim chiaro", "Eliotropo", "Ecru", "Fiore di granturco", "Foglia di tè", "Fucsia", "Fucsia pallido", "Giada", "Giallo", "Giallo Napoli", "Giallo pastello", "Giallo scuolabus", "Glicine", "Glicine", "Grano", "Granata", "Grigio", "Grigio asparago", "Grigio ardesia scuro", "Grigio cenere", "Grigio rosso chiaro", "Grigio té verde", "Incarnato prugna", "Indaco", "Indaco elettrico", "Indaco scuro", "International Klein Blue", "Kaki", "Kaki scuro", "Lampone", "Lavanda", "Lavanda pallido", "Lavanda rosata", "Limone", "Limone Crema", "Lilla", "Lime", "Lino", "Magenta", "Magenta chiaro", "Malva", "Malva chiaro", "Mandarino", "Marrone", "Marrone chiaro", "Marrone pastello", "Marrone-rosso", "Marrone sabbia chiaro", "Marrone scuro", "Melanzana", "Nero", "Ocra", "Orchidea", "Oliva chiaro", "Oro", "Oro vecchio", "Oro vivo", "Oro vivo smorto", "Ottone antico", "Papaya", "Pervinca", "Pesca", "Pesca scuro", "Pesca-arancio", "Pesca-giallo", "Porpora", "Prugna", "Rame", "Rosa", "Rosa-arancio", "Rosa-ciliegia", "Rosso mattone chiario", "Rosa Mountbatten", "Rosa pallido", "Rosa pastello", "Rosso sangue", "Rosa scuro", "Rosa shocking", "ROsa medio", "Rosa vivo", "Rosso", "Rosso aragosta", "Rosso cardinale", "Rosso fragola", "Rosso-rosa", "Rosso pompeiano", "Rosso Tiziano", "Rosso veneziano", "Rosso violetto chiaro", "Rosso violaceo", "Rosso mattone", "Rosso fuoco", "Rosso pomodoro", "Rubino", "Sabbia", "Salmone", "Salmone scuro", "Scarlatto", "Scarlatto scuro", "Seppia", "Terra di Siena", "Terra di Siena Bruciata", "Tè verde", "Tè verde scuro", "Turchese", "Turchese chiaro", "Turchese pallido", "Turchese scuro", "Uovo di pettirosso", "Verde", "Verde caraibi", "Verde foresta", "Verde chiaro", "Verde-giallo", "Verde mare chiaro", "Verde marino", "Verde menta", "Verde menta chiaro", "Verde muschio", "Verde oliva", "Verde olivastro", "Verde oliva-giallo", "Verde oliva scuro", "Verde pastello", "Verde pastello scuro", "Verde pino", "Verde primavera", "Verde primavera scuro", "Verde scuro", "Verde smeraldo", "Verde Veronese", "Vermiglio", "Viola-melanzana", "Viola scuro", "Zaffiro" }));

        jComboBoxNumPorte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "5" }));

        jComboBoxCarburante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Benzina", "Diesel", "GPL", "Metano", "Ibrida", "Elettrica" }));

        jComboBoxAnnoProduz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", " " }));

        jLabelDescrizione.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDescrizione.setText("Descrizione");

        jTextAreaDescrizione.setColumns(20);
        jTextAreaDescrizione.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescrizione);

        jLTipologia.setText("Tipologia");

        jLCilindrata.setText("Cilindrata");

        jButtonInvioDati.setText("Invia i dati");
        jButtonInvioDati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvioDatiActionPerformed(evt);
            }
        });

        jButtonInserimentoImg.setText("Inserisci un'immagine");
        jButtonInserimentoImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserimentoImgActionPerformed(evt);
            }
        });

        jTextFieldInserimentoImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldInserimentoImg.setText("--immagine non presente--");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelDescrizione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInserimentoImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBaseAsta)
                            .addComponent(jLKmPercorsi, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jLCilindrata)
                            .addComponent(jLAnnoProduz)
                            .addComponent(jLCarburante)
                            .addComponent(jLNumPorte)
                            .addComponent(jLColore)
                            .addComponent(jLTipologia)
                            .addComponent(jLModello)
                            .addComponent(jLMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxNumPorte, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCarburante, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAnnoProduz, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldKmPercorsi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldBaseAsta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxMarca, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldModello, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxColore, javax.swing.GroupLayout.Alignment.TRAILING, 0, 285, Short.MAX_VALUE)
                            .addComponent(jTextFieldCilindrata)
                            .addComponent(jComboBoxTipologia, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2)
                    .addComponent(jTextFieldInserimentoImg)
                    .addComponent(jButtonInvioDati, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLMarca)
                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModello, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLModello))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTipologia)
                    .addComponent(jComboBoxTipologia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxColore, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLColore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxNumPorte, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNumPorte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCarburante, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLCarburante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAnnoProduz, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAnnoProduz))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLCilindrata)
                    .addComponent(jTextFieldCilindrata, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldKmPercorsi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLKmPercorsi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBaseAsta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBaseAsta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescrizione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInserimentoImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInserimentoImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInvioDati)
                .addContainerGap(2, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInserimentoImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserimentoImgActionPerformed
        jFileChooser1.showOpenDialog(this);
        fileSelected = jFileChooser1.getSelectedFile();
        jTextFieldInserimentoImg.setText("Converto immagine...");
        new ImageLoader(this, fileSelected).start();
    }//GEN-LAST:event_jButtonInserimentoImgActionPerformed

    private void jButtonInvioDatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvioDatiActionPerformed
        boolean flag;
        if (jTextFieldModello.getText().isEmpty() || jTextFieldBaseAsta.getText().isEmpty() || jTextAreaDescrizione.getText().isEmpty()
                || jTextFieldCilindrata.getText().isEmpty() || jTextFieldKmPercorsi.getText().isEmpty() || fileSelected == null) {
            JOptionPane.showMessageDialog(this, "Compila tutti i campi");
            flag = false;
        } else {
            try {
                Integer.parseInt(jTextFieldCilindrata.getText());
                Integer.parseInt(jTextFieldKmPercorsi.getText());
                Float.parseFloat(jTextFieldBaseAsta.getText());
                flag = true;
            } catch (Exception e) {
                flag = false;
                JOptionPane.showMessageDialog(this, "Devi inserire un numero");
            }
            if (flag) {
                new SubmitListener(Applet1.this, getParameter("user"), jTextAreaDescrizione.getText(), jTextFieldBaseAsta.getText(),
                        jTextFieldCilindrata.getText(), jTextFieldKmPercorsi.getText(), jComboBoxAnnoProduz.getSelectedItem().toString(),
                        jComboBoxCarburante.getSelectedItem().toString(), jComboBoxColore.getSelectedItem().toString(),
                        jComboBoxMarca.getSelectedItem().toString(), jComboBoxNumPorte.getSelectedItem().toString(),
                        jComboBoxTipologia.getSelectedItem().toString(), jTextFieldModello.getText(), imgBase64).execute();
            }
        }
    }//GEN-LAST:event_jButtonInvioDatiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInserimentoImg;
    private javax.swing.JButton jButtonInvioDati;
    private javax.swing.JComboBox jComboBoxAnnoProduz;
    private javax.swing.JComboBox jComboBoxCarburante;
    private javax.swing.JComboBox jComboBoxColore;
    private javax.swing.JComboBox jComboBoxMarca;
    private javax.swing.JComboBox jComboBoxNumPorte;
    private javax.swing.JComboBox jComboBoxTipologia;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLAnnoProduz;
    private javax.swing.JLabel jLBaseAsta;
    private javax.swing.JLabel jLCarburante;
    private javax.swing.JLabel jLCilindrata;
    private javax.swing.JLabel jLColore;
    private javax.swing.JLabel jLKmPercorsi;
    private javax.swing.JLabel jLMarca;
    private javax.swing.JLabel jLModello;
    private javax.swing.JLabel jLNumPorte;
    private javax.swing.JLabel jLTipologia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDescrizione;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescrizione;
    private javax.swing.JTextField jTextFieldBaseAsta;
    private javax.swing.JTextField jTextFieldCilindrata;
    public static javax.swing.JTextField jTextFieldInserimentoImg;
    private javax.swing.JTextField jTextFieldKmPercorsi;
    private javax.swing.JTextField jTextFieldModello;
    // End of variables declaration//GEN-END:variables
}
