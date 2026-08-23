package Utility;

public class Ruolo{
    private int id;
    private String nomeRuolo;

    public Ruolo (int idRuolo, String nomeRuolo){
        this.id=idRuolo;
        this.nomeRuolo=nomeRuolo;
    }

    public Ruolo(Ruolo r){
        this.id=r.id;
        this.nomeRuolo=r.nomeRuolo;
    }

    public int getIdRuolo(){
        return id;
    }

    public String getNomeRuolo(){
        return nomeRuolo;
    }
}