package Item;

import java.util.HashMap;
import java.util.Map;

public class OrdineFornitore {
    private Map<Articolo, Integer> listaOrdine;
    private int numeroOrdine;

    public OrdineFornitore(Map<Articolo,Integer> listaOrdine, int numeroOrdine){
        this.listaOrdine=new HashMap<>(listaOrdine);
        this.numeroOrdine=numeroOrdine;
    }


}
