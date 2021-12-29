package com.trade_accounting.repositories;

import com.trade_accounting.models.InvoiceToBuyerListProducts;
import com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceToBuyerListProductsRepository extends JpaRepository<InvoiceToBuyerListProducts, Long>, JpaSpecificationExecutor<InvoiceToBuyerListProducts> {
    @Query("select new com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto(" +
            "e.id," +
            "e.product.id," +
            "e.supplierAccount.id," +
            "e.amount," +
            "e.price," +
            "e.sum," +
            "e.percentNds," +
            "e.nds," +
            "e.total) from InvoiceToBuyerListProducts e")
    List<InvoiceToBuyerListProductsDto> getAll();

    @Query("select new com.trade_accounting.models.dto.InvoiceToBuyerListProductsDto(" +
            "e.id," +
            "e.product.id," +
            "e.supplierAccount.id," +
            "e.amount," +
            "e.price," +
            "e.sum," +
            "e.percentNds," +
            "e.nds," +
            "e.total) from InvoiceToBuyerListProducts e where e.id =:id")
    List<InvoiceToBuyerListProductsDto> getById(@Param("id") Long id);
}