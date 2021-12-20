package com.trade_accounting.repositories;

import com.trade_accounting.models.IssuedInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ssplaksa
 * @version 1.0.0
 */

@Repository
public interface IssuedInvoiceRepository extends JpaRepository<IssuedInvoice, Long> {
}
