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
            throw new IllegalArgumentException("Vendita non presente");
        }
        registro.remove(p);
    }

    public double totalePagamenti() {
        double tot = 0;
        for (Pagamento p: registro)
            tot += 1;
        return tot;
    }

    public List<Pagamento> getPagamenti() {
        return getLista();
    }
}


