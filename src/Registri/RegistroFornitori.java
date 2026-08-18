package Registri;

import Persone.Fornitore;

import java.util.List;

public class RegistroFornitori extends RegistroAbstract<Fornitore> {

    public void aggiungiFornitore(Fornitore f) {
        registro.add(f);
    }

    public void rimuoviFornitore(Fornitore f) {
        if (!registro.contains(f)) {
            throw new IllegalArgumentException("Fornitore non presente");
        }
        registro.remove(f);
    }
    public List<Fornitore> getFornitore() {
        return getLista();
    }
}
