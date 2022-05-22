/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Ugovor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class TableModel extends AbstractTableModel{
    ArrayList<Ugovor> ugovori;

    public TableModel() {
        ugovori = new ArrayList<>();
    }

    public TableModel(ArrayList<Ugovor> ugovori) {
        this.ugovori = ugovori;
    }
    
    @Override
    public int getRowCount() {
        return ugovori.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Ugovor ug = ugovori.get(i);
        switch(i1){
            case 0: return ug.getPretplatnik();
            case 1: return ug.getPaket();
            case 2: return ug.getVrsta();
            case 3:  if(ug.getDodatneUsl()==1) return "DA"; else return "NE";
            case 4: return ug.getDatumEvidentiranja();
            case 5: return ug.getCena();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int i1) {
           switch(i1){
            case 0: return "Pretplatnik";
            case 1: return "Paket";
            case 2: return "Vrsta ugovora";
            case 3:  return "Dodatne usluge";
            case 4: return "Datum evidentiranja";
            case 5: return "Cena";
            default: return "";    }   
    
}

    public ArrayList<Ugovor> getUgovori() {
        return ugovori;
    }

    public void setUgovori(ArrayList<Ugovor> ugovori) {
        this.ugovori = ugovori;
        fireTableDataChanged();
    }
    
    public void dodaj(Ugovor u){
        ugovori.add(u);
        fireTableDataChanged();
    }
    public void obrisi(int red){
        ugovori.remove(red);
        fireTableDataChanged();
    }
}