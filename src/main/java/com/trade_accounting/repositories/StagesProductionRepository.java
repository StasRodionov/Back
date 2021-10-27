package com.trade_accounting.repositories;

import com.trade_accounting.models.StagesProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.stream.Collectors;

public interface StagesProductionRepository extends JpaRepository <StagesProduction, Long> {

    /**
     * Find a Stages of production by ID which contains in input set of IDs
     * @param ids - income set of IDs to search for
     * @return set of StagesProduction
     */
    Set<StagesProduction> getStagesProductionsByIdIn(Set<Long> ids);

    /**
     * The same method as upper one, but done manual with a StreamAPI
     * @param ids - income set of IDs to search for
     * @return set of StagesProduction
     */
    default Set<StagesProduction> getStagesProductionByIds(Set<Long> ids) {
        return findAll().stream()
                .filter(stagesProduction -> ids.contains(stagesProduction.getId()))
                .collect(Collectors.toSet());
    }
}

// Этапы