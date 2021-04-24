package com.trade_accounting.services.interfaces;

import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public interface SearchableService<MODEL, DTO> {
    List<DTO> search(Specification<MODEL> spec);
}
