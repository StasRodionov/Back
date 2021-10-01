package com.trade_accounting.repositories;

import com.trade_accounting.models.PrepaymentReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrepaymentReturnRepository extends JpaRepository<PrepaymentReturn, Long>, JpaSpecificationExecutor<PrepaymentReturn> {
}
