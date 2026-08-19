package Registri;

import Persone.Persona;

import java.util.List;

public class RegistroAnagrafico extends RegistroAbstract<Persona>{
    public void aggiungiPersona(Persona p) {
        registro.add(p);
    }

    public void rimuoviPersona(Persona p) {
        if (!registro.contains(p)) {
            throw new IllegalArgumentException("Persona non presente in anagrafica");
        }
        registro.remove(p);
        System.out.println("La persona " + p +" è stata eliminata con successo dall'anagrafica");
    }

    public int totalePersonale() {
        return registro.size();
    }

    public List<Persona> getPersonale() {
        return getLista();
    }
}
