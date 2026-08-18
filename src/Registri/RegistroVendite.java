package Registri;

import java.util.List;

public class RegistroVendite extends RegistroAbstract<Vendita> {

    public void aggiungiVendita(Vendita v) {
        listaRegistro.add(v);
    }

    public void rimuoviVendita(Vendita v) {
        if (!listaRegistro.contains(v)) {
            throw new IllegalArgumentException("Vendita non presente");
        }
        listaRegistro.remove(v);
    }

    public double totaleVendite() {
        double tot = 0;
        for (Vendita v : listaRegistro) tot += v.prezzoTotale();
        return tot;
    }

    public List<Vendita> getVendite() {
        return getLista();
    }
}
