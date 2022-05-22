/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Paket;
import domen.Pretplatnik;
import domen.Ugovor;
import java.util.ArrayList;
import view.Glavna;

/**
 *
 * @author Korisnik
 */
public class KontrolerServer {
    private static KontrolerServer instanca;
    private DBBroker db;
    private Glavna gl;

    public void setGl(Glavna gl) {
        this.gl = gl;
    }

    public Glavna getGl() {
        return gl;
    }
    public void osvezi() throws Exception{
        gl.napuniTabelu1();
    }
    private KontrolerServer() {
        db = new DBBroker();
    }
    public static KontrolerServer getInstanca(){
        if(instanca==null) instanca= new KontrolerServer();
        return instanca;
    }

    public ArrayList<Paket> vratiPakete() throws Exception{
        try {
            db.otvoriKonekciju();
            ArrayList<Paket> pr = db.vratiPakete();
            db.zatvoriKonekciju();
            return pr;
        } catch (Exception e) {
            db.zatvoriKonekciju();
            throw e;
        }
    }

    public ArrayList<Pretplatnik> vratiPretpl() throws Exception{
        try {
            db.otvoriKonekciju();
            ArrayList<Pretplatnik> pr = db.vratiPretpl();
            db.zatvoriKonekciju();
            return pr;
        } catch (Exception e) {
            db.zatvoriKonekciju();
            throw e;
        }
    }

    public boolean sacuvaj(ArrayList<Ugovor> ugovori) throws Exception {
        try {
            db.otvoriKonekciju();
            for (Ugovor ugovor : ugovori) {
                db.sacuvaj(ugovor);
            }
            db.commit();
            db.zatvoriKonekciju();
            return true;
        } catch (Exception e) {
            db.rollback();
            db.zatvoriKonekciju();
            throw e;
        }
    }

    public ArrayList<Ugovor> vratiUg() throws Exception{
         try {
            db.otvoriKonekciju();
            ArrayList<Ugovor> pr = db.vratiUg();
            db.zatvoriKonekciju();
            return pr;
        } catch (Exception e) {
            db.zatvoriKonekciju();
            throw e;
        }
    }

    public void obrisi(Ugovor ug) throws Exception {
         try {
            db.otvoriKonekciju();
            db.obrisi(ug);
            db.commit();
            db.zatvoriKonekciju();
            
        } catch (Exception e) {
            db.rollback();
            db.zatvoriKonekciju();
            throw e;
        }
    }
    
    
    
    
    
    
    
    
    
    
}
