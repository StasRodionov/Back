package com.trade_accounting.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column( columnDefinition="VARCHAR(1024)")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Employee taskEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Employee taskAuthor;

    @NotNull
    @Column(name = "creation_date_time", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime creationDateTime;

    @Column(name = "deadline_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime deadlineDateTime;

    private boolean completed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskComment> taskComments;

}
