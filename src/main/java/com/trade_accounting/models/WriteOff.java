package com.trade_accounting.models;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "write_off")
public class WriteOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "date")
    LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    Company company;

    @Column(name = "is_sent")
    @ColumnDefault("false")
    Boolean isSent = false;

    @Column(name = "is_print")
    @ColumnDefault("false")
    Boolean isPrint = false;

    @Column(name = "comment")
    String comment;

    @OneToMany(fetch = FetchType.LAZY)
    List<WriteOffProduct> writeOffProducts;

}
