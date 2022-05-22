/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Ugovor implements Serializable{
    private int ugID;
    private Date datumEvidentiranja;
    private String vrsta;
    private int dodatneUsl;
    private double cena;
    private Paket paket;
    private Pretplatnik pretplatnik;

    public Ugovor() {
    }

    public Ugovor(int ugID, Date datumEvidentiranja, String vrsta, int dodatneUsl, double cena, Paket paket, Pretplatnik pretplatnik) {
        this.ugID = ugID;
        this.datumEvidentiranja = datumEvidentiranja;
        this.vrsta = vrsta;
        this.dodatneUsl = dodatneUsl;
        this.cena = cena;
        this.paket = paket;
        this.pretplatnik = pretplatnik;
    }

    public Pretplatnik getPretplatnik() {
        return pretplatnik;
    }

    public void setPretplatnik(Pretplatnik pretplatnik) {
        this.pretplatnik = pretplatnik;
    }

    public int getUgID() {
        return ugID;
    }

    public void setUgID(int ugID) {
        this.ugID = ugID;
    }

    public Date getDatumEvidentiranja() {
        return datumEvidentiranja;
    }

    public void setDatumEvidentiranja(Date datumEvidentiranja) {
        this.datumEvidentiranja = datumEvidentiranja;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public int getDodatneUsl() {
        return dodatneUsl;
    }

    public void setDodatneUsl(int dodatneUsl) {
        this.dodatneUsl = dodatneUsl;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }
    
    
}
