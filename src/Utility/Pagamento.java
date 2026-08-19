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

    public long getId(){
        return vendita.getId();
    }

    public String toString(){
        return "Pagamento con id di vendita="+ vendita.getId() + "realizzato con metodo di pagamento"+ metodoPagamento;
    }
}
