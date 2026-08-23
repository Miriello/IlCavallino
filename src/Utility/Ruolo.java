package Utility;

public class Ruolo{
    private int idRuolo;
    private String nomeRuolo;

    public Ruolo (int idRuolo, String nomeRuolo){
        this.idRuolo=idRuolo;
        this.nomeRuolo=nomeRuolo;
    }

    public Ruolo(Ruolo r){
        this.idRuolo=r.idRuolo;
        this.nomeRuolo=r.nomeRuolo;
    }

    public int getIdRuolo(){
        return idRuolo;
    }

    public String getNomeRuolo(){
        return nomeRuolo;
    }
}