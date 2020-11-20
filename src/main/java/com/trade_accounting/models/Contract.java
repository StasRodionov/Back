package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "number")
        private String number;

        @Column(name = "contract_date", columnDefinition = "date default current_date")
        private LocalDate contractDate;

        @ManyToOne
        @Column(nullable = false)
        private Company company;

        //PaymentAccount пока нет
        //@ManyToOne
        //private PaymentAccount paymentAccount;

        @Column(nullable = false)
        @ManyToOne
        private Contractor contractor;

        @Column(name = "sum", columnDefinition = "Money default 0.0")
        private BigDecimal sum;

        @Column(name = "payer_vat")
        private Boolean payerVat;

        @Column(name = "address")
        private String address;

        @Column(name = "comment_to_address")
        private String commentToAddress;

        @Column(name = "leader")
        private String leader;

        @Column(name = "leader_manager_position")
        private String leaderManagerPosition;

        @Column(name = "leader_signature")
        private String leaderSignature;

        @Column(name = "chief_accountant")
        private String chiefAccountant;

        @Column(name = "chief_accountant_signature")
        private String chiefAccountantSignature;

        @Column(name = "stamp")
        private String stamp;
    }

}
