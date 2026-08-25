package Item;

public class Allergene {
    private String nome;
    private int codiceAllergene;

    public Allergene(String nome, int codiceAllergene){
        this.nome = nome;
        this.codiceAllergene= codiceAllergene;
    }

    public Allergene(Allergene a){
        this.nome=a.nome;
        this.codiceAllergene=a.codiceAllergene;
    }

    @Override
    public String toString(){
        return nome;
    }

    public int getCodiceAllergene() {
        return codiceAllergene;
    }
}
