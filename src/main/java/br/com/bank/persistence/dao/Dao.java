package br.com.bank.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get();
    void save(T data);
    void update(T data);
    void delete(Long id);
    List<T> getAll();
}
