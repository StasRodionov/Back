package com.trade_accounting.models;


import com.sun.source.util.TaskListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retail_operation_with_points")
@Builder
public class RetailOperationWithPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Номер
    @Column(name = "number")
    private Long number;

    //Текущее время
    @Column(name = "current_time")
    private LocalDateTime currentTime;

    //Тип операции(начисление, списание)
    @Column(name = "type_operation")
    private String typeOperation;

    //Колличество баллов
    @Column(name = "number_of_points")
    private Long numberOfPoints;

    //Дата начисления
    @Column(name = "accrual_date")
    private LocalDateTime accrualDate;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private BonusProgram bonusProgram;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Contractor contractor;

    @OneToOne(fetch = FetchType.LAZY)
    private Task task;

    @OneToMany(fetch = FetchType.LAZY)
    private List<File> files;
}
