package com.trade_accounting.utils.fias;

import com.trade_accounting.models.Invoice;
import com.trade_accounting.models.dto.InvoiceDto;
import com.trade_accounting.utils.InvoiceDto_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class test implements Specification<InvoiceDto> {

    private final List<String> types;

    public test(List<String> types) {
        this.types = types;
    }

    private Specification<Invoice> typeIn(List<String> types) {
        return (root, query, cb) -> {
            if (types != null && !types.isEmpty()) {
                return root.get(String.valueOf(InvoiceDto_.date)).in(types);
            } else {
                // always-true predicate, means that no filtering would be applied
                return cb.and();
            }
        };
    }

    @Override
    public Predicate toPredicate(Root<InvoiceDto> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (types != null && !types.isEmpty()) {
            return root.get(InvoiceDto_.date).in(types);
        } else {
            // always-true predicate, means that no filtering would be applied
            return cb.and();
        }
    }
}