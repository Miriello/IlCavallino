package Registri;

import java.util.ArrayList;
import java.util.List;

public class RegistroAbstract<T> implements Registro<T> {
    protected List<T> registro = new ArrayList<>();

    public void aggiungi(T t){
        registro.add(t);
    }

    public void rimuovi(T t){
        registro.remove(t);
    }

    public boolean contiene(T t){
        return registro.contains(t);
    }

    public List<T> getLista() {
        return new ArrayList<>(registro);
    }
}
