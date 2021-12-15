package com.trade_accounting.models;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "file")
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String extension;

    @Column
    private String placement;

    @Column
    private String employee;

    @Column(unique = true)
    private String key;

    @Column(name = "upload_date_time")
    private LocalDateTime uploadDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public File(Long id,
                String name,
                String extension,
                String placement,
                String employee,
                String key,
                LocalDateTime uploadDateTime) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.placement = placement;
        this.employee = employee;
        this.key = key;
        this.uploadDateTime = uploadDateTime;
    }
}