package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productions")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TechnicalCard technicalCard;

   /*
    @ManyToOne(fetch = FetchType.LAZY)
    private RequestProductions requestsProductions;

    @ManyToOne(fetch = FetchType.LAZY)
    private TechOperations techOperations;
<<<<<<< HEAD
    
=======

>>>>>>> dev
 */

}
