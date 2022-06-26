package com.trade_accounting.models.entity.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount")
@Builder
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "name")
    @NotNull
    String name;

    @Column(name = "type")
    @NotNull
    String type;

    @Column(name = "is_bonus_program")
    Boolean isBonusProgram;

    @OneToOne
    @JoinColumn(name = "bonus_program_id")
    BonusProgram bonusProgram;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Discount discount = (Discount) o;
        return id != null && Objects.equals(id, discount.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
