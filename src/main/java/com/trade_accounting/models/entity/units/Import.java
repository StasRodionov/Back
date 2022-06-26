package com.trade_accounting.models.entity.units;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String task;

    @Column(name="start_date")
    private String start;

    @Column(name="end_date")
    private String end;

    @Column
    private String status;

    public Import(String task, String start, String end, String status) {
        this.task = task;
        this.start = start;
        this.end = end;
        this.status = status;
    }
}
