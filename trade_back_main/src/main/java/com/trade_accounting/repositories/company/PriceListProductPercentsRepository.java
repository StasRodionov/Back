package com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.PriceListProductPercents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListProductPercentsRepository extends JpaRepository<PriceListProductPercents, Long>,
        JpaSpecificationExecutor<PriceListProductPercents> {
    List<PriceListProductPercents> findAllByPriceListId(Long id);
}
