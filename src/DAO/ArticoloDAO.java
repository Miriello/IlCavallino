package DAO;

import Item.Articolo;

import java.util.List;

public interface ArticoloDAO<T extends Articolo> {
    List<T> findAll();
    void insert(T articolo);
    void update(T articolo);
    void delete(T articolo);
}
