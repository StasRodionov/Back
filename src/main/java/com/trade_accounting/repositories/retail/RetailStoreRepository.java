package com.trade_accounting.repositories.retail;

import com.trade_accounting.models.entity.retail.RetailStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailStoreRepository extends JpaRepository<RetailStore, Long> {

}
