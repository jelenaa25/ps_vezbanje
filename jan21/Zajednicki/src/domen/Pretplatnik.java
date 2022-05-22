/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class Pretplatnik implements Serializable{
    private int pretplID;
    private String ime;
    private String prezime;

    public Pretplatnik() {
    }

    public Pretplatnik(int pretplID, String ime, String prezime) {
        this.pretplID = pretplID;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getPretplID() {
        return pretplID;
    }

    public void setPretplID(int pretplID) {
        this.pretplID = pretplID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    
}
