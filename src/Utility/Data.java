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
        int hash = 17;
        hash = 19 * hash + giorno;
        hash = 19 * hash + mese;
        hash = 19 * hash + anno;
        return hash;
    }

    public boolean equals(Object o){
        if (o==null) return false;
        if(!(o instanceof Data)) return false;
        if(o==this) return true;
        Data d = (Data) o;
        return (d.giorno==this.giorno) && (d.mese==this.mese) && (d.anno==this.anno);
    }
}
