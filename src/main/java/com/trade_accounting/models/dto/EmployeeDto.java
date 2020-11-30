package com.trade_accounting.models.dto;

import com.trade_accounting.models.Department;
import com.trade_accounting.models.Image;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Role;

import javax.validation.constraints.Pattern;
import java.util.Set;

public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middleName;

    private String sortNumber;

    private String phone;

    @Pattern(regexp = "([0-9]+){12}")
    private String inn;

    private String description;

    private String image;

    private String email;

    private String password;

    private Department department;

    private Position position;

    private Set<Role> roles;

    private Image images;

}
