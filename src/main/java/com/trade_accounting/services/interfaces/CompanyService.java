package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAll();

    Company getById(Long id);

    Company getByEmail(String email);

    void create(); //todo добавить в параметры CompanyDTO

    void update(); //todo добавить в параметры CompanyDTO

    void deleteById(Long id);
}
