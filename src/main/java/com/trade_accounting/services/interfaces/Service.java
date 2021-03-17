package com.trade_accounting.services.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface Service<T> {

    Collection<T> getAll();

    Optional<T> getById(Long id);

    T create(T taskDTO);

    T update(T taskDTO);

    void deleteById(Long id);
}
