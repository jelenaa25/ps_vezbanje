/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.Socket;
import logika.Komunikacija;
import view.Glavna;


/**
 *
 * @author Korisnik
 */
public class Klijent {
    Socket socket;
    public static void main(String[] args) throws IOException, Exception {
         new Klijent().connect();
         //prikazi gl formuuuu
         new Glavna().setVisible(true);
    }
    
    public void connect() throws IOException{
        socket = new Socket("localhost", 9000);
        Komunikacija.getInstanca().setSocket(socket);
    }
}
