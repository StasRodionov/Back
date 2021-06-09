package com.trade_accounting.models;

import lombok.AllArgsConstructor;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corrections")
public class Correction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    private Boolean correctionIsSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    private Boolean correctionIsPrint = false;

    @Column(name = "write_off")
    @ColumnDefault("false")
    private Boolean writeOff = false;

    @Column(name = "comment")
    private String comment;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<CorrectionProduct> correctionProducts;
//    Будет раскомментировано тогда, когда будет добавлена модель CorrectionProduct - в след мердж реквесте
}
