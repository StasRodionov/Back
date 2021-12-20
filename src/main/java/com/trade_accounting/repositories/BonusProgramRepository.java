package com.trade_accounting.repositories;

import com.trade_accounting.models.BonusProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@Repository
public interface BonusProgramRepository extends JpaRepository<BonusProgram, Long> {

}
