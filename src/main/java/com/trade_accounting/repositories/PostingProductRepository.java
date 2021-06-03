package com.trade_accounting.repositories;


import com.trade_accounting.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostingProductRepository extends JpaRepository<PostingProduct, Long>,
        JpaSpecificationExecutor<PostingProduct> {

    @Query("SELECT DISTINCT pp FROM PostingProduct pp LEFT JOIN FETCH pp.invoiceProduct ip")
    List<PostingProduct> getAll();

    @Query("SELECT pp FROM PostingProduct pp LEFT JOIN FETCH pp.invoiceProduct ip WHERE pp.id =: id")
    PostingProduct getPostingById(@Param("id") Long id);
}

