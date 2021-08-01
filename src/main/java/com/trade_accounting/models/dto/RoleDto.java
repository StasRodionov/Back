package com.trade_accounting.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    private String name;

    private String sortNumber;

    public RoleDto(String name, String sortNumber) {
        this.name = name;
        this.sortNumber = sortNumber;
    }
}
