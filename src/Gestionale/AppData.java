package Gestionale;

import Item.Ingrediente;
import Item.Prodotto;
import Gestori.GestioneMenu;
import Gestori.GestioneScorte;
import Persone.Fornitore;
import Registri.RegistroFornitori;
import Registri.RegistroVendite;
import Utilità.Data;
import Utilità.Sede;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stato condiviso dell'applicazione. In produzione ogni dato verrebbe dal DB;
 * qui serve come layer unico a cui tutte le finestre fanno riferimento.
 */
public class AppData {
    public static final GestioneMenu      MENU      = new GestioneMenu();
    public static final GestioneScorte    SCORTE    = new GestioneScorte();
    public static final RegistroVendite   VENDITE   = new RegistroVendite();
    public static final RegistroFornitori FORNITORI = new RegistroFornitori();

    static {
        Data futuro = new Data(31, 12, 2026);

        MENU.addProdotto(new Prodotto("Pasta al Pomodoro",     8.50,  Arrays.asList("pasta", "pomodoro", "basilico"),            futuro));
        MENU.addProdotto(new Prodotto("Bistecca alla Griglia", 18.00, Arrays.asList("carne", "rosmarino", "aglio"),              futuro));
        MENU.addProdotto(new Prodotto("Risotto ai Funghi",     12.00, Arrays.asList("riso", "funghi", "parmigiano"),             futuro));
        MENU.addProdotto(new Prodotto("Tiramisù",               5.00, Arrays.asList("mascarpone", "savoiardi", "caffè"),         futuro));
        MENU.addProdotto(new Prodotto("Panna Cotta",            4.50, Arrays.asList("panna", "zucchero", "vaniglia"),            futuro));

        SCORTE.aggiungiArticolo(new Ingrediente("Pasta",        new Data(30,  6, 2026)));
        SCORTE.aggiungiArticolo(new Ingrediente("Pomodoro",     new Data(25,  5, 2026)));
        SCORTE.aggiungiArticolo(new Ingrediente("Carne",        new Data(21,  5, 2026)));
        SCORTE.aggiungiArticolo(new Ingrediente("Riso",         new Data(1,  12, 2026)));
        SCORTE.aggiungiArticolo(new Ingrediente("Funghi",       new Data(22,  5, 2026)));
        SCORTE.aggiungiArticolo(new Ingrediente("Mascarpone",   new Data(25,  5, 2026)));

        FORNITORI.aggiungi(new Fornitore("12345678901", "Carni Selezionate SRL",
                new ArrayList<>(), new Sede("Milano", "Lombardia", "20100"), "ordini@carniselezionate.it"));
        FORNITORI.aggiungi(new Fornitore("98765432109", "Prodotti Freschi SpA",
                new ArrayList<>(), new Sede("Roma", "Lazio", "00100"), "ordini@prodottifreschi.it"));
    }
}
