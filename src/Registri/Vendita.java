package Registri;

import Cose.Prodotto;

public class Vendita {
    private Prodotto prodotto;
    private int quantita;

    public Vendita(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public double prezzoTotale() {
        return prodotto.getPrezzo() * quantita;
    }

    public Prodotto getProdotto() { return prodotto; }
    public int getQuantita() { return quantita; }

    @Override
    public String toString() {
        return prodotto.getNome() + " x" + quantita + " = €" + String.format("%.2f", prezzoTotale());
    }
}
