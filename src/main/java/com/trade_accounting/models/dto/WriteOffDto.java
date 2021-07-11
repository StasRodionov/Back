package com.trade_accounting.models.dto;


import com.trade_accounting.models.Company;
import com.trade_accounting.models.Warehouse;
import com.trade_accounting.models.WriteOff;
import com.trade_accounting.models.WriteOffProduct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteOffDto {

    Long id;

    @NotNull
    LocalDateTime date;

    @NotNull
    Long warehouseId;

    @NotNull
    Long companyId;

    @ColumnDefault("false")
    Boolean isSent = false;

    @ColumnDefault("false")
    Boolean isPrint = false;

    @Column(name = "comment")
    String comment;

    List<Long> writeOffProductIds;
}
