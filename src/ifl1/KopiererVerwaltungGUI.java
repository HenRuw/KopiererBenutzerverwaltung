package ifl1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Autor: %AUTHOR%
// Datum: %DATE%

public class KopiererVerwaltungGUI extends JFrame {
    // Anfang Attribute
    private JLabel labPINGenerator = new JLabel();
    private JLabel labBenutzerId = new JLabel();
    private JLabel labPin = new JLabel();
    private JTextField tfBenutzerID = new JTextField();
    private JTextField tfPin = new JTextField();
    private JButton btId = new JButton();
    private JButton btSuchePin = new JButton();
    private JButton btNeuesVerzeichnis = new JButton();
    private JButton btergaenzen = new JButton();
    private JLabel labVersion = new JLabel();
    private JTextArea taTab = new JTextArea("");
    private JScrollPane taTabScrollPane = new JScrollPane(taTab);
    private JButton btSortname = new JButton();
    private JButton btsortAnzahl = new JButton();
    // Ende Attribute
    private BenutzerVerwalterA verwalter = new BenutzerVerwalterA();

    public KopiererVerwaltungGUI(String title) {
        // Frame-Initialisierung
        super(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 662;
        int frameHeight = 311;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        labPINGenerator.setBounds(16, 8, 110, 20);
        labPINGenerator.setText("Benutzerverwalter");
        cp.add(labPINGenerator);
        labBenutzerId.setBounds(16, 40, 110, 20);
        labBenutzerId.setText("Benutzername/ Id");
        cp.add(labBenutzerId);
        labPin.setBounds(16, 72, 110, 20);
        labPin.setText("PIN");
        cp.add(labPin);
        tfBenutzerID.setBounds(144, 40, 150, 20);
        cp.add(tfBenutzerID);
        tfPin.setBounds(144, 72, 150, 20);
        cp.add(tfPin);
        btId.setBounds(8, 104, 110, 25);
        btId.setText("Suche: Name");
        btId.setMargin(new Insets(2, 2, 2, 2));
        btId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btId_ActionPerformed(evt);
            }
        });
        cp.add(btId);
        btSuchePin.setBounds(144, 104, 110, 25);
        btSuchePin.setText("Suche: PIN");
        btSuchePin.setMargin(new Insets(2, 2, 2, 2));
        btSuchePin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btSuchePin_ActionPerformed(evt);
            }
        });
        cp.add(btSuchePin);
        btNeuesVerzeichnis.setBounds(32, 168, 267, 25);
        btNeuesVerzeichnis.setText("erzeuge Beispieldaten");
        btNeuesVerzeichnis.setMargin(new Insets(2, 2, 2, 2));
        btNeuesVerzeichnis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btNeuesVerzeichnis_ActionPerformed(evt);
            }
        });
        cp.add(btNeuesVerzeichnis);
        btergaenzen.setBounds(32, 200, 267, 25);
        btergaenzen.setText("ergaenze Benutzer");
        btergaenzen.setMargin(new Insets(2, 2, 2, 2));
        btergaenzen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btergaenzen_ActionPerformed(evt);
            }
        });
        cp.add(btergaenzen);
        labVersion.setBounds(16, 232, 334, 20);
        labVersion.setText("Version");
        cp.add(labVersion);
        taTabScrollPane.setBounds(336, 8, 305, 225);
        cp.add(taTabScrollPane);
        btSortname.setBounds(336, 240, 131, 25);
        btSortname.setText("sortiere: Namen");
        btSortname.setMargin(new Insets(2, 2, 2, 2));
        btSortname.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btSortname_ActionPerformed(evt);
            }
        });
        cp.add(btSortname);
        btsortAnzahl.setBounds(488, 240, 147, 25);
        btsortAnzahl.setText("sortiere: Kopienanzahl");
        btsortAnzahl.setMargin(new Insets(2, 2, 2, 2));
        btsortAnzahl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btsortAnzahl_ActionPerformed(evt);
            }
        });
        cp.add(btsortAnzahl);
        // Ende Komponenten

        setVisible(true);
        labVersion.setText(verwalter.gibVersion());
    } // end of public PINGeneratorGUI

    // Anfang Methoden
    public void btId_ActionPerformed(ActionEvent evt) {
        String pin, name;
        name = tfBenutzerID.getText();
        Benutzer b = verwalter.sucheBenutzerName(name);
        if (b != null) {
            tfPin.setText(b.gibPIN());
        } else {
            tfPin.setText("---");
        }
    } // end of btId_ActionPerformed

    public void btSuchePin_ActionPerformed(ActionEvent evt) {
        verwalter.sortierePIN();
        taTab.setText(verwalter.erstelleAusgabe());
        String pin;
        pin = tfPin.getText();
        Benutzer b = verwalter.suchePIN(pin);
        if (b != null) {
            tfBenutzerID.setText(b.gibName());
        } else {
            tfBenutzerID.setText("---");
        }
    } // end of btPin_ActionPerformed

    public void btNeuesVerzeichnis_ActionPerformed(ActionEvent evt) {
        verwalter.beispielBelegung();
        taTab.setText(verwalter.erstelleAusgabe());

    } // end of btNeuesVerzeichnis_ActionPerformed
    public void btergaenzen_ActionPerformed(ActionEvent evt) {
        String name = tfBenutzerID.getText();
        Benutzer b = verwalter.sucheBenutzerName(name); //currently not working
        if (b == null) {
            verwalter.neuerBenutzer(name);
        } else {
            tfPin.setText("bereits vorhanden");
        }
        taTab.setText(verwalter.erstelleAusgabe());
    } // end of btergaenzen_ActionPerformed

    public void btSortname_ActionPerformed(ActionEvent evt) {
        verwalter.sortiereNamen();
        taTab.setText(verwalter.erstelleAusgabe());
    } // end of btSortname_ActionPerformed

    public void btsortAnzahl_ActionPerformed(ActionEvent evt) {
        verwalter.sortiereVerbrauch();
        taTab.setText(verwalter.erstelleAusgabe());
    } // end of btsortAnzahl_ActionPerformed

    // Ende Methoden

    public static void main(String[] args) {
        new KopiererVerwaltungGUI("KopiererVerwaltungGUI");
    } // end of main

} // end of class PINGeneratorGUI
