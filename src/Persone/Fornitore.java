package Persone;

import Item.Articolo;
import Item.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class Fornitore {
    private String partitaIva;
    private String ragioneSociale;
    private String email;
    private List<Articolo> beniForniti;

    public Fornitore(String partitaIva, String ragioneSociale, String email , List<Ingrediente> articoli) {
        this.partitaIva = partitaIva;
        this.ragioneSociale = ragioneSociale;
        this.email = email;
        this.beniForniti = new ArrayList<>(articoli);
    }

    public String getPartitaIva() { return partitaIva; }
    public String getRagioneSociale() { return ragioneSociale; }
    public String getEmail() { return email; }
    public List<Articolo> getBeniForniti() { return beniForniti; }

    @Override
    public String toString() {
        return ragioneSociale + " (P.IVA: " + partitaIva + ")";
    }

}
