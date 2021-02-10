package com.trade_accounting.services.impl;

import com.trade_accounting.models.dto.EmployeeDto;
import com.trade_accounting.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    final private EmployeeService employeeService;

    @Autowired
    public EmployeeDetailsServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
        EmployeeDto employeeDto = employeeService.getByEmail(employeeEmail);

        if (employeeDto == null) {
            throw new UsernameNotFoundException(employeeEmail);
        }
        return employeeDto;
    }
}
