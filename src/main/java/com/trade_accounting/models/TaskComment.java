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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "comment", columnDefinition="VARCHAR(1024)")
    private String commentContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee publisher;

    @NotNull
    @Column(name = "published_date_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime publishedDateTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
}
