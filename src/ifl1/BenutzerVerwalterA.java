package ifl1;


public class BenutzerVerwalterA {

    private int maxBenutzerzahl = 100;
    private int aktBenutzerzahl = 0;
    private int maxPIN = 0;
    private Benutzer[] benutzerDaten;

    public BenutzerVerwalterA() {
        benutzerDaten = new Benutzer[maxBenutzerzahl];
        for (int i = 0; i < benutzerDaten.length; i++) {
            benutzerDaten[i] = null;
        }
    }

    public String gibVersion() {
        return "A: ARRAY, Sortieren  Kriterium Name/ Verbrauch/ PIN";
    }

    private String erzeugePIN() {
        int intervall = 15000/maxBenutzerzahl;
        int zufallszahl = (int) (Math.random()*intervall) + 1;
        maxPIN = maxPIN + zufallszahl;
        String pin = "" + maxPIN;
        if (pin.length() == 1) {
            pin = "000"+pin;
        }
        if (pin.length() == 2) {
            pin = "00"+pin;
        }
        if (pin.length() == 3) {
            pin = "0"+pin;
        }
        return pin;
    }

    public void neuerBenutzer(String pName) {
        boolean vorhanden;
        String pin;
        Benutzer b = null;
        do {
            pin = erzeugePIN();
            if (aktBenutzerzahl > 0) {
                b = lineareSuchePIN(pin);
            }
            if (b == null) {
                vorhanden = false;
            } else {
                vorhanden = true;
            }
        } while (vorhanden);
        if (!vorhanden) {
            eintragen(pName,pin);
            //TODO System.out.println(pName+" "+pin+" anzahl: "+aktBenutzerzahl);
        }
    }

    public void beispielBelegung() {
        //TODO erzeuge eine neue Liste mit 100 Benutzern
    }

    private int pinNr(String pPIN) {
        return Integer.parseInt(pPIN);
    }

    private void eintragen(String pName, String pPIN) {
        Benutzer neu = new Benutzer(pName,pPIN);
        int anzahl = (int) (Math.random()*1000);
        neu.erhoeheVerbrauch(anzahl);
        benutzerDaten[aktBenutzerzahl] = neu;
        System.out.println((aktBenutzerzahl)+" "+benutzerDaten[aktBenutzerzahl].gibName()+" "+benutzerDaten[aktBenutzerzahl].gibPIN());
        aktBenutzerzahl++;
    }

    private Benutzer lineareSucheName(String pName) {
        //TODO lineare Suche nach Benutzer nach Name
        return null;
    }

    private Benutzer lineareSuchePIN(String pPIN) {
        //TODO lineare Suche nach Benutzer nach Pin
        return null;
    }

    private Benutzer binaereItSuchePIN(String pSuchPIN) {
        //TODO binaere Suche nach Benutzer nach Pin
        return null;
    }

    private Benutzer binaereRekSuchePIN(String pSuchPIN, int pLinks, int pRechts) {
        //TODO binaer rekursive Suche nach Benutzer nach Pin
        return null;
    }

    public Benutzer suchePIN(String pSuchPIN) {
        //TODO suche Benutzer nach Pin ohne Vorgabe des Verfahrens
        return null;
    }

    public Benutzer sucheBenutzerName(String pName) {
        //TODO suche Benutzer nach namen ohne Vorgabe des Verfahrens
        return null;
    }

    public String erstelleAusgabe() {
        String ausgabe = "";
        for (int i = 0; i < aktBenutzerzahl; i++) {
            String zeile;
            if (i < 9) {
                zeile = "  "+(i+1)+" ";
            } else {
                zeile = ""+(i+1)+" ";
            }
            zeile = zeile + "  "+ benutzerDaten[i].gibName();
            zeile = zeile + "\t "+ benutzerDaten[i].gibAnzahl();
            ausgabe = ausgabe + zeile + "\n";
        }
        return ausgabe;
    }

    public void sortiereVerbrauch() {
        //TODO Wähle hier dein Lieblingsverfahren
    }

    public void sortiereNamen() {
        //TODO Wähle dein Zweitlieblingsverfahren
    }

    public void sortierePIN() {
        //TODO Quicksort
        int n = aktBenutzerzahl;
        quicksort(0, n-1);
    }

    private void quicksort (int lo, int hi) {
        //TODO muss noch implementiert werden
    }

    private void tausche(int i, int j) {
        Benutzer b = benutzerDaten[i];
        benutzerDaten[i] = benutzerDaten[j];
        benutzerDaten[j] = b;
    }

}

