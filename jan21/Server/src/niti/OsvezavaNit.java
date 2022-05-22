/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.KontrolerServer;

/**
 *
 * @author Korisnik
 */
public class OsvezavaNit extends Thread{
    ServerSocket ss;

    public void setSs(ServerSocket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        while (!ss.isClosed()) {            
            try {
                KontrolerServer.getInstanca().osvezi();
            } catch (Exception ex) {
                Logger.getLogger(OsvezavaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsvezavaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
