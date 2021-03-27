package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.LegalDetail;
import com.trade_accounting.models.dto.LegalDetailDto;

import java.util.List;

public interface LegalDetailService {

    List<LegalDetailDto> getAll();

    LegalDetailDto getById(Long id);

    LegalDetailDto create(LegalDetailDto legalDetailDto);

    LegalDetailDto update(LegalDetailDto legalDetailDto);

    void deleteById(Long id);

    void create(LegalDetail legalDetail);
}
