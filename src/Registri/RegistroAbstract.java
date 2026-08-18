package Registri;

import java.util.ArrayList;
import java.util.List;

public class RegistroAbstract<T> implements Registro<T> {
    protected List<T> listaRegistro = new ArrayList<>();
    public void aggiungi(T t){
        listaRegistro.add(t);
    }
    public void rimuovi(T t){
        listaRegistro.remove(t);
    }
    public boolean contiene(T t){
        return listaRegistro.contains(t);
    }
    public List<T> getLista() {
        return new ArrayList<>(listaRegistro);
    }
}
