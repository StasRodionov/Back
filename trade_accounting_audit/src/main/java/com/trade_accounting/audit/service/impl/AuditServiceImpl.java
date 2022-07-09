package com.trade_accounting.audit.service.impl;

import com.trade_accounting.audit.model.Audit;
import com.trade_accounting.audit.model.Employee;
import com.trade_accounting.audit.repository.AuditRepository;
import com.trade_accounting.audit.service.interfaces.AuditService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public List<Audit> getAll() {
        return auditRepository.findAll();
    }

    @Override
    public Audit getById(Long id) {
        return auditRepository.getOne(id);
    }

    @Override
    public Audit create(Audit audit) {
        auditRepository.saveAndFlush(audit);
        return audit;
    }

    @Override
    public Audit update(Audit audit) {
        auditRepository.saveAndFlush(audit);
        return audit;
    }

    @Override
    public void deleteById(Long id) {
        auditRepository.deleteById(id);
    }

    @Override
    public List<Audit> searchByFilter(String search) {
        return auditRepository.findAll().stream()
                .filter(audit -> isAuditContainsSearchString(audit, search))
                .collect(Collectors.toList());
    }

    @Override
    public List<Audit> quickSearch(String text) {
        return auditRepository.findAll().stream()
                .filter(audit -> isAuditContainsSearchString(audit, text))
                .collect(Collectors.toList());
    }

    @Override
    public List<Audit> search(Specification<Audit> spec) {
        return auditRepository.findAll(spec);
    }

    private boolean isAuditContainsSearchString(Audit audit, String search) {
        return stringifyAudit(audit).toLowerCase().contains(search.toLowerCase());
    }

    private String stringifyAudit(Audit audit) {
        Employee employee = audit.getEmployee();
        return audit.getId().toString() + " " +
                audit.getDescription() + " " +
                audit.getDate().toString() + " " +
                employee.getId().toString() + " " +
                employee.getFirstName() + " " +
                employee.getMiddleName() + " " +
                employee.getLastName() + " " +
                employee.getEmail();
    }
}
