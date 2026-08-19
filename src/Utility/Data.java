package Utility;

public class Data {
    private int giorno;
    private int mese;
    private int anno;

    public  Data (int giorno, int mese, int anno){
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    public Data (Data d){
        this.giorno = d.giorno;
        this.mese = d.mese;
        this.anno = d.anno;
    }

    public int getGiorno() { return giorno; }
    public int getMese() { return mese; }
    public int getAnno() { return anno; }

    public String toString() {
        return giorno + "/" + mese + "/" + anno;
    }

    public int hashCode(){
        return 1;
    }
}
