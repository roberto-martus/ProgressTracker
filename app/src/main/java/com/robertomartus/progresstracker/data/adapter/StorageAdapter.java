package com.robertomartus.progresstracker.data.adapter;

import java.util.List;

/**
 * Created by Leonid on 02.01.2015.
 */
public interface StorageAdapter<E,K> {

    E loadByKey(K key);
    List<? extends E> loadAll();

    void insert(E entity);
    void insert(Iterable<? extends E> entities);
    void insertOrReplace(E entity);

    void deleteByKey(K key);
    void deleteByKeys(Iterable<K> keys);

    void refresh(E entity);

    void update(E entity);
    void update(Iterable<? extends E> entities);

}
