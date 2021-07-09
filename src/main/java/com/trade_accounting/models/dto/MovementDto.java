package com.trade_accounting.models.dto;

import com.trade_accounting.models.Company;
import com.trade_accounting.models.CorrectionProduct;
import com.trade_accounting.models.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {

    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Warehouse warehouseFrom;

    @NotNull
    private Warehouse warehouseTo;

    @NotNull
    private Company company;

    private Boolean isSent = false;

    private Boolean isPrint = false;

    private String comment;

    private List<CorrectionProduct> correctionProducts;
}
