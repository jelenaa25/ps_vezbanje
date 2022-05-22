/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

/**
 *
 * @author Korisnik
 */
public class KontrolerKlijent {
    private static KontrolerKlijent instanca;

    private  KontrolerKlijent() {
    }

    public static KontrolerKlijent getInstanca() {
       if(instanca == null) instanca = new KontrolerKlijent();
        return instanca;
    }

    
    
}
