package com.trade_accounting.models.entity.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GridEmployeeKey implements Serializable {

    @Column(name = "grid_id")
    Integer gridId;

    @Column(name = "employee_id")
    Long employeeId;

}
