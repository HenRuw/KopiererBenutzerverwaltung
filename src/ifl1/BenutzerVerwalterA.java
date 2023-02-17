package ifl1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BenutzerVerwalterA {

    private int maxBenutzerzahl = 100;
    private int aktBenutzerzahl = 0;
    private int maxPIN = 0;
    private Benutzer[] benutzerDaten;

    public BenutzerVerwalterA() {
        benutzerDaten = new Benutzer[maxBenutzerzahl];
        Arrays.fill(benutzerDaten, null);
    }

    public String gibVersion() {
        return "A: ARRAY, Sortieren  Kriterium Name/ Verbrauch/ PIN";
    }

    private String erzeugePIN() {
        int intervall = 15000/maxBenutzerzahl;
        int zufallszahl = (int) (Math.random()*intervall) + 1;
        maxPIN = maxPIN + zufallszahl;
        String pin = "" + maxPIN;
        while (pin.length() < 4) {
            pin = "0" + pin;
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
            //System.out.println(pName+" "+pin+" anzahl: "+aktBenutzerzahl);
        }
    }

    public void beispielBelegung() {
        for (int i = 0; i < maxBenutzerzahl; i++) {
            neuerBenutzer("Benutzer " + (i+1));
        }
    }

    private int pinNr(String pPIN) {
        return Integer.parseInt(pPIN);
    }

    private void eintragen(String pName, String pPIN) {
        Benutzer neu = new Benutzer(pName,pPIN);
        int anzahl = (int) (Math.random()*1000);
        neu.erhoeheVerbrauch(anzahl);
        benutzerDaten[aktBenutzerzahl] = neu;
        //System.out.println((aktBenutzerzahl)+" "+benutzerDaten[aktBenutzerzahl].gibName()+" "+benutzerDaten[aktBenutzerzahl].gibPIN());
        aktBenutzerzahl++;
    }

    private Benutzer lineareSucheName(String pName) {
        for (int i = 0; i < aktBenutzerzahl; i++) {
            if (benutzerDaten[i] != null && benutzerDaten[i].gibName().equals(pName)) {
                return benutzerDaten[i];
            }
        }
        return null;
    }

    private Benutzer lineareSuchePIN(String pPIN) {
        for (int i = 0; i < aktBenutzerzahl; i++) {
            if (benutzerDaten[i] != null && benutzerDaten[i].gibPIN().equals(pPIN)) {
                return benutzerDaten[i];
            }
        }
        return null;
    }

    private Benutzer binaereItSuchePIN(String pSuchPIN, int pLinks, int pRechts) {
        sortierePIN();
        int mitte = (pRechts+pLinks)/2;
        int compared = pSuchPIN.compareTo(benutzerDaten[mitte].gibPIN());

        if (pLinks==pRechts){
            if(compared == 0) return benutzerDaten[mitte];
            return null;
        }

        return switch (compared) {
            case 0 -> benutzerDaten[mitte];
            case -1 -> binaereItSuchePIN(pSuchPIN, pLinks, mitte - 1);
            default -> binaereItSuchePIN(pSuchPIN, mitte + 1, pRechts);
        };
    }

    public Benutzer suchePIN(String pSuchPIN) {
        return(binaereItSuchePIN(pSuchPIN,0,benutzerDaten.length));
    }

    public Benutzer sucheBenutzerName(String pName) {
        return(lineareSucheName(pName));
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

            if (i < 9) {
                zeile += "\t";
            }

            zeile = zeile + "\t "+ benutzerDaten[i].gibAnzahl();

            ausgabe = ausgabe + zeile + "\n";
        }
        return ausgabe;
    }
    public void sortiereVerbrauch() {
        final int RADIX = 10;

        // declare and initialize bucket[]
        List<Benutzer>[] bucket = new ArrayList[RADIX];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Benutzer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) {
            maxLength = true;

            // split input between lists
            for (int j = 0; j < benutzerDaten.length; j++) {
                Benutzer i = benutzerDaten[j];
                tmp = i.gibAnzahl() / placement;
                bucket[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }

            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                List<Benutzer> benutzerList = bucket[b];
                for (int j = 0; j < benutzerList.size(); j++) {
                    Benutzer i = benutzerList.get(j);
                    benutzerDaten[a++] = i;
                }
                bucket[b].clear();
            }

            // move to next digit
            placement *= RADIX;
        }
    }


    public void sortiereNamen() {
        final int RADIX = 10;

        // declare and initialize bucket[]
        List<Benutzer>[] bucket = new ArrayList[RADIX];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Benutzer>();
        }

        // sort
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        int index = 0;
        while (!maxLength) {
            maxLength = true;

            // split input between lists
            for (int j = 0; j < aktBenutzerzahl; j++) {
                Benutzer i = benutzerDaten[j];
                
                if (i.gibName().length() > index){
                    System.out.println((int) i.gibName().charAt(index));
                    tmp = ((int) i.gibName().charAt(index)) / placement;
                }
                else {
                    tmp = 0;
                }
                

                bucket[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }

            // empty lists into input array
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                List<Benutzer> benutzerList = bucket[b];
                for (int j = 0; j < benutzerList.size(); j++) {
                    Benutzer i = benutzerList.get(j);
                    benutzerDaten[a++] = i;
                }
                bucket[b].clear();
            }

            // move to next digit
            placement *= RADIX;
            index++;
        }
    }

    public void sortierePIN() {
        //Quicksort
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

