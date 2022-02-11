package com.trade_accounting.repositories.invoice;

import com.trade_accounting.models.entity.invoice.InvoiceReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceReceivedRepository extends JpaRepository<InvoiceReceived, Long> {
}
