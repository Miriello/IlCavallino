package Registri;

import Utility.Vendita;

import java.util.List;

public class RegistroVendite extends RegistroAbstract<Vendita> {

    public void aggiungiVendita(Vendita v) {
        registro.add(v);
    }

    public void rimuoviVendita(Vendita v) {
        if (!registro.contains(v)) {
            throw new IllegalArgumentException("Vendita non presente");
        }
        registro.remove(v);
        System.out.println("La vendita con ID= " + v.getId() + " è stata rimossa con successo");
    }

    public double totaleVendite() {
        double tot = 0;
        for (Vendita v : registro)
            tot += v.prezzoTotale();
        return tot;
    }

    public List<Vendita> getVendite() {

        return getLista();
    }
}
