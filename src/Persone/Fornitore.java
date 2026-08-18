package Persone;

import Item.Articolo;
import Utilità.Sede;
import java.util.ArrayList;
import java.util.List;

public class Fornitore {
    private String partitaIva;
    private String ragioneSociale;
    private String email;
    private Sede sede;
    private List<Articolo> beniForniti;

    public Fornitore(String partitaIva, String ragioneSociale, List<Articolo> articoli, Sede sede, String email) {
        this.partitaIva = partitaIva;
        this.ragioneSociale = ragioneSociale;
        this.sede = sede;
        this.email = email;
        this.beniForniti = new ArrayList<>(articoli);
    }

    public String getPartitaIva() { return partitaIva; }
    public String getRagioneSociale() { return ragioneSociale; }
    public String getEmail() { return email; }
    public Sede getSede() { return sede; }
    public List<Articolo> getBeniForniti() { return beniForniti; }

    @Override
    public String toString() {
        return ragioneSociale + " (P.IVA: " + partitaIva + ")";
    }
}
