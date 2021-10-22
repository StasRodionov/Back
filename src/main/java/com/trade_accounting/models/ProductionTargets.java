package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "production_targets")
public class ProductionTargets { // класс "Производственные задания"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //поле "№"

    @Column
    private LocalDateTime date; //поле "время"

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company; //поле "организация"

    @Column
    private LocalDateTime deliveryPlannedMoment; // поле "Планируемая дата производства"

    @Column
    private String materialWarehouse; //Заглушка для поля "Склад для материалов"

    @Column
    private String productionWarehouse; // Заглушка для поля "Склад для продукции"

    @Column
    private LocalDateTime productionStart; // поле "начало производства"

    @Column
    private LocalDateTime productionEnd; // поле "завершение производства"

    @Column
    @ColumnDefault("false")
    private Boolean shared; // поле "общий доступ"

    @Column
    private String Owner; // Заглушка для поля "Владелец-отдел"

    @Column
    private String employeeOwner; // Заглушка для поля "Владелец-сотрудник"

    @Column
    @ColumnDefault("false")
    private Boolean published; // поле "отправлено"

    @Column
    @ColumnDefault("false")
    private Boolean printed; // поле "напечатано"

    @Column
    private String description; // поле "комментарий"

    @Column
    private LocalDateTime updated; // поле "когда изменен"

    @Column
    private String updatedByName; // Заглушка для поля "Кто изменил"

}
