package Utility;

public class Pagamento {
    private int  idVendita;
    private String metodoPagamento;

    public Pagamento(int idVendita, String metodoPagamento){
        this.idVendita= idVendita;
        this.metodoPagamento=metodoPagamento;
    }

    public int getidVendita(){
        return idVendita;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public String toString(){
        return "Pagamento con id di vendita="+ idVendita + "realizzato con metodo di pagamento"+ metodoPagamento;
    }
}
