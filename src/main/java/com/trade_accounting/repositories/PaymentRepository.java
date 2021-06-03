package com.trade_accounting.repositories;

import com.trade_accounting.models.Payment;
import com.trade_accounting.models.dto.PaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {

    @Query("select new com.trade_accounting.models.dto.PaymentDto(" +
            "e.id," +
            "e.typeOfPayment," +
            "e.number," +
            "e.time," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.project.id," +
            "e.sum"+
            ") from Payment e")
    List<PaymentDto> getAll();

    @Query("select new com.trade_accounting.models.dto.PaymentDto(" +
            "e.id," +
            "e.typeOfPayment," +
            "e.number," +
            "e.time," +
            "e.company.id," +
            "e.contractor.id," +
            "e.contract.id," +
            "e.project.id," +
            "e.sum"+
            ") from Payment e")
    PaymentDto getById(@Param("id") Long id);

    void deleteAllByContractId(@Param("id") Long id);

    @Query("from Payment e" +
            " where lower(concat(e.company.name, ' ', e.contractor.name, ' ', e.number))" +
            " like lower(concat('%', :req, '%'))")
    List<Payment> search(@Param("req") String request);


}
