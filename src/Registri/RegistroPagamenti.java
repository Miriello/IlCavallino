package Registri;

import Utility.Pagamento;
import Utility.Vendita;

import java.util.List;

public class RegistroPagamenti extends RegistroAbstract<Pagamento>{
    public void aggiungiPagamento(Pagamento p) {
        registro.add(p);
    }

    public void rimuoviPagamento(Pagamento p) {
        if (!registro.contains(p)) {
            throw new IllegalArgumentException("Pagamento non presente");
        }
        registro.remove(p);
        System.out.println("Il pagamento" + p.getVendita().getId() +" è stato rimosso con successo");
    }

    public double totalePagamenti() {
        return registro.size();
    }

    public List<Pagamento> getPagamenti() {
        return getLista();
    }


}


