/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Paket;
import domen.Pretplatnik;
import domen.Ugovor;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Operations;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.ResponseType;
import komunikacija.Sender;
import logika.KontrolerServer;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread{
     Socket socket;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!socket.isClosed()){
            try {
                Request request = (Request) new Receiver(socket).receive();
                Response response=handleRequest(request);
                new Sender(socket).send(response);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Response handleRequest(Request request) throws Exception {
        switch(request.getOperation()){
            case Operations.PAKETI: return vratiPak(request);
            case Operations.PRETPL: return vratiPretpl(request);
            case Operations.SACUVAJ: return sacuvaj(request);
        }
        return null;
    }

    private Response vratiPak(Request request) throws Exception {
        Response response = new Response();
        ArrayList<Paket> paketi = KontrolerServer.getInstanca().vratiPakete();
        if(paketi != null){
            response.setResult(paketi);
            response.setResponseType(ResponseType.SUCCESS);
        }
        return response;
    }

    private Response vratiPretpl(Request request) throws Exception {
          Response response = new Response();
        ArrayList<Pretplatnik> paketi = KontrolerServer.getInstanca().vratiPretpl();
        if(paketi != null){
            response.setResult(paketi);
            response.setResponseType(ResponseType.SUCCESS);
        }
        return response;
    }

    private Response sacuvaj(Request request) throws Exception {
        Response response = new Response();
        ArrayList<Ugovor> ugovori = (ArrayList<Ugovor>) request.getArgument();
        boolean uspeh = KontrolerServer.getInstanca().sacuvaj(ugovori);
        if(uspeh){
            response.setResponseType(ResponseType.SUCCESS);
        }
        return response;
    }
}
