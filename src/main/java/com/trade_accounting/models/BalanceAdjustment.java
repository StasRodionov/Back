package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс-модель корректировки
 *
 * @param id          - номер корректировки
 * @param date        - время корректировки
 * @param company     - компания
 * @param contractor  - контрагент
 * @param account     - счет
 * @param cashOffice  - касса
 * @param comment     - комментарий
 * @param dateChanged - когда изменен
 * @param whoChanged  - кто изменил

 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "balance_adjustments")
public class BalanceAdjustment {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @NotNull
    private String account;

    @NotNull
    private String cashOffice;

    @NotNull
    private String comment;

    @NotNull
    private String dateChanged;

    @NotNull
    private String whoChanged;

}