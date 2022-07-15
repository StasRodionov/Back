package com.trade_accounting.audit.service.interfaces;

import com.trade_accounting.audit.model.Audit;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AuditService {
    List<Audit> getAll();

    Audit getById(Long id);

    Audit create(Audit audit);

    Audit update(Audit audit);

    void deleteById(Long id);

    List<Audit> searchByFilter(String search);

    List<Audit> quickSearch(String text);

    List<Audit> search(Specification<Audit> spec);
}
