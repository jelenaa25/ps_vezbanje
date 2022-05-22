/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.net.Socket;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {
        private Socket socket;
        private static Komunikacija instanca;

    private Komunikacija() {
    }

    public static Komunikacija getInstanca() {
        if(instanca==null) instanca= new Komunikacija();
        return instanca;
    }
    
    
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
  
    public Response nesto(Request request) throws Exception {
        new Sender(socket).send(request);
        System.out.println("Zahtev serveru je poslat...");
        return (Response) new Receiver(socket).receive();
    }
}
