package Utility;

public class Pagamento {
    private Vendita vendita;
    private String metodoPagamento;

    public Pagamento(Vendita v, String mp){
        this.vendita=v;
        this.metodoPagamento=mp;
    }

    public Vendita getVendita(){
        return vendita;
    }

    public long getId(Pagamento p){
        return p.getVendita().getId();
    }
}
