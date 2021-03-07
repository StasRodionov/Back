package com.trade_accounting.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sort_number")
    private String sortNumber;

    @Transient
    private byte[] content;

    @Transient
    private String fileName;

    public Image(String imageUrl, String sortNumber) {
        this.imageUrl = imageUrl;
        this.sortNumber = sortNumber;
    }
}
