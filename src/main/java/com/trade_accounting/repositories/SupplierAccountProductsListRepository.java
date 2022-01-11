package com.trade_accounting.repositories;

import com.trade_accounting.models.SupplierAccountProductsList;
import com.trade_accounting.models.dto.SupplierAccountProductsListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAccountProductsListRepository extends JpaRepository<SupplierAccountProductsList, Long>, JpaSpecificationExecutor<SupplierAccountProductsList> {
    @Query("select new com.trade_accounting.models.dto.SupplierAccountProductsListDto(" +
            "e.id," +
            "e.product.id," +
            "e.supplierAccount.id," +
            "e.amount," +
            "e.price," +
            "e.sum," +
            "e.percentNds," +
            "e.nds," +
            "e.total) from SupplierAccountProductsList e")
    List<SupplierAccountProductsListDto> getAll();

    @Query("select new com.trade_accounting.models.dto.SupplierAccountProductsListDto(" +
            "e.id," +
            "e.product.id," +
            "e.supplierAccount.id," +
            "e.amount," +
            "e.price," +
            "e.sum," +
            "e.percentNds," +
            "e.nds," +
            "e.total) from SupplierAccountProductsList e where e.id =:id")
    List<SupplierAccountProductsList> getById(@Param("id") Long id);
}
