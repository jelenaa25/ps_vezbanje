/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Glavna;

/**
 *
 * @author Korisnik
 */
public class ServerThread extends Thread{
        private ServerSocket serverSocket;
    private ArrayList<ObradaKlijentskihZahteva> klijenti;

    public ServerThread() throws IOException {
        serverSocket = new ServerSocket(9000);
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
       while(!serverSocket.isClosed()){
           try {
               Socket ss = serverSocket.accept();
               System.out.println("Klijent povezan");
               ObradaKlijentskihZahteva o = new ObradaKlijentskihZahteva(ss);
               klijenti.add(o);
               o.start();
           } catch (IOException ex) {
               Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public static void main(String[] args) throws IOException, Exception {
        ServerThread s1 = new ServerThread();
        s1.start();
        
        new Glavna().setVisible(true);
        
        OsvezavaNit sss = new OsvezavaNit();
        sss.setSs(s1.getServerSocket());
        sss.start();
    }
}
