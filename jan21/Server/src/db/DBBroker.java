/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Paket;
import domen.Pretplatnik;
import domen.Ugovor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class DBBroker {
    private Connection connection;
   
   ////promeniiiiiii parametre za baaaazuuu
   public void otvoriKonekciju() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjan21", "root", "root");
            connection.setAutoCommit(false);
            System.out.println("Uspesno otvorena konekcija na bazu.");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Neuspesna konekcija na bazu.");
        }
    }
   
           public void zatvoriKonekciju(){
        try {
            connection.close();
            System.out.println("Uspesno zatvorena konekcija na bazu.");
        } catch (SQLException ex) {
            
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Neuspesno zatvorena konekcija na bazu.");
        }
    }
    
        public void commit(){
        try {
            connection.commit();
            System.out.println("Uspesan commit!");

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Neuspesan commit!");
        }
    }
    public void rollback(){
        try {
            connection.rollback();
            System.out.println("Uspesan rollback!");

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Neuspesan rollback!");

        }
    }

    public ArrayList<Pretplatnik> vratiPretpl() throws SQLException {
         String upit = "select* from pretplatnik";
        ArrayList<Pretplatnik> paketi = new ArrayList<>();
        Statement ss = connection.createStatement();
        ResultSet rs = ss.executeQuery(upit);
        while(rs.next()){
            Pretplatnik p = new Pretplatnik(rs.getInt("PretplatnikID"), rs.getString("Ime"), rs.getString("Prezime"));
            paketi.add(p);
        }
        rs.close();
        ss.close();
        return paketi;
    }

    public ArrayList<Paket> vratiPakete() throws SQLException {
        String upit = "select* from paket";
        ArrayList<Paket> paketi = new ArrayList<>();
        Statement ss = connection.createStatement();
        ResultSet rs = ss.executeQuery(upit);
        while(rs.next()){
            Paket p = new Paket(rs.getInt("PaketID"), rs.getString("Naziv"), rs.getString("Opis"), rs.getDouble("Cena"), rs.getInt("Aktivan"));
            paketi.add(p);
        }
        rs.close();
        ss.close();
        return paketi;
    }

    public void sacuvaj(Ugovor ugovor) throws SQLException {
        String upit = "insert into ugovor(DatumEvidentiranja, VrstaUgovora, DodatneUsluge, Cena, PaketID, PretplatnikID) values(?,?,?,?,?,?)";
        PreparedStatement p = connection.prepareStatement(upit);
        
        p.setDate(1, new Date(ugovor.getDatumEvidentiranja().getTime()));
        p.setString(2, ugovor.getVrsta());
         p.setInt(3, ugovor.getDodatneUsl());
          p.setDouble(4, ugovor.getCena());
           p.setInt(5, ugovor.getPaket().getPaketID());
            p.setInt(6, ugovor.getPretplatnik().getPretplID());
            
            p.executeUpdate();
            p.close();
    }

    public ArrayList<Ugovor> vratiUg() throws SQLException {
        String upit = "select* from ugovor join paket on ugovor.PaketID = paket.PaketID join pretplatnik on ugovor.UgovorID = pretplatnik.PretplatnikID";
        ArrayList<Ugovor> paketi = new ArrayList<>();
        Statement ss = connection.createStatement();
        ResultSet rs = ss.executeQuery(upit);
        while(rs.next()){
            Pretplatnik pretplatnik = new Pretplatnik(rs.getInt("PretplatnikID"), rs.getString("Ime"), rs.getString("Ime"));
            Paket paket = new Paket(rs.getInt("PaketID"), rs.getString("Naziv"), rs.getString("Opis"), rs.getDouble("Cena"), rs.getInt("Aktivan"));
            Ugovor ug = new Ugovor(rs.getInt("UgovorID"), new Date(rs.getDate("DatumEvidentiranja").getTime()), rs.getString("VrstaUgovora"), rs.getInt("DodatneUsluge"), rs.getDouble("Cena"), paket, pretplatnik);
            paketi.add(ug);
        }
        rs.close();
        ss.close();
        return paketi;
    }

    public void obrisi(Ugovor ug) throws SQLException {
        String upit = "delete from ugovor where ugovor.UgovorID ="+ug.getUgID();
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate(); ps.close();
    }
    
    
}
