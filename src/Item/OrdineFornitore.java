package Item;

import java.util.HashMap;
import java.util.Map;

public class OrdineFornitore {
    private AccordoFornitura af;
    private Map<Articolo, Integer> listaOrdine;
    private int numeroOrdine;

    public OrdineFornitore(AccordoFornitura af, Map<Articolo,Integer> listaOrdine, int numeroOrdine){
        this.af=af;
        this.listaOrdine=new HashMap<>(listaOrdine);
        this.numeroOrdine=numeroOrdine;
    }


}
