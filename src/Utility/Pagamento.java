package Utility;

public class Pagamento {
    private int  idVendita;
    private double importo;
    private String metodoPagamento;

    public Pagamento(int idVendita, double importo, String metodoPagamento){
        this.idVendita= idVendita;
        this.importo=importo;
        this.metodoPagamento=metodoPagamento;
    }

    public int getIdVendita(){
        return idVendita;
    }

    public double getImporto(){
        return importo;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public String toString(){
        return "Pagamento con id di vendita="+ idVendita + "realizzato con metodo di pagamento"+ metodoPagamento;
    }
}
