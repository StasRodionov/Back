package com.trade_accounting.repositories;

import com.trade_accounting.models.dto.TechCardDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechCardRepository extends JpaRepository<TechCardDto, Long> {
}
