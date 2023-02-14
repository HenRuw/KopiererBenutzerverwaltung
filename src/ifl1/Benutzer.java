package ifl1;

public class Benutzer {
    private String name;
    private String pin;
    private int limit;
    private int verbrauch;

    public Benutzer(String pName, String pPIN) {
        name = pName;
        pin = pPIN;
        limit = 1000;
    }

    public void setzeLimit(int pLimit) {
        limit = pLimit;
    }

    public void erhoeheVerbrauch(int pAnzahl) {
        verbrauch = verbrauch + pAnzahl;
    }

    public int gibAnzahl() {
        return limit - verbrauch;
    }


    public String gibPIN() {
        return pin;
    }

    public String gibName() {
        return name;
    }

}
