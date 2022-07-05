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
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String digitCode;

    @Column(nullable = false, length = 2)
    private String twoLetterCode;

    @Column(nullable = false, length = 3)
    private String threeLetterCode;

    public Country(String type,
                   String shortName,
                   String fullName,
                   String digitCode,
                   String twoLetterCode,
                   String threeLetterCode) {
        this.type = type;
        this.shortName = shortName;
        this.fullName = fullName;
        this.digitCode = digitCode;
        this.twoLetterCode = twoLetterCode;
        this.threeLetterCode = threeLetterCode;
    }
}
