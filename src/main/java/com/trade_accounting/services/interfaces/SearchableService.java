package com.trade_accounting.services.interfaces;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public interface SearchableService<T, E> extends Service<T> {
    Collection<T> search(Specification<E> specification);
}
