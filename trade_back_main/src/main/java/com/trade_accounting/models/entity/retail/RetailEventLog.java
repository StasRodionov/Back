package com.trade_accounting.models.entity.retail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "retail_event_log")
public class RetailEventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "operation_id")
    private Long operationId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "sell_point")
    private String sellPoint;

    private String initiator;

    private String details;

    private String api;
}
