package Persone;


import Item.Ingrediente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fornitore {
    private String partitaIva;
    private String ragioneSociale;
    private String email;
    private Map<Ingrediente, Double> beniForniti;

    public Fornitore(String partitaIva, String ragioneSociale, String email , Map<Ingrediente,Double> articoli) {
        this.partitaIva = partitaIva;
        this.ragioneSociale = ragioneSociale;
        this.email = email;
        this.beniForniti = new HashMap<>(articoli);
    }

    public String getPartitaIva() { return partitaIva; }
    public String getRagioneSociale() { return ragioneSociale; }
    public String getEmail() { return email; }
    public Map<Ingrediente,Double> getBeniForniti() { return new HashMap<>(beniForniti); }

    @Override
    public String toString() {
        return ragioneSociale + " (P.IVA: " + partitaIva + ")";
    }

}
