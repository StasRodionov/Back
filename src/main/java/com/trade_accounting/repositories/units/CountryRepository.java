package com.trade_accounting.repositories.units;

import com.trade_accounting.models.entity.units.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    @Query("FROM Country c " +
            "WHERE LOWER (CONCAT(c.id, ' ', c.type, ' ', c.shortName, ' ', c.fullName, ' ', c.digitCode, ' ', c.twoLetterCode, ' ', c.threeLetterCode)) " +
            "LIKE LOWER (CONCAT('%', :symbol, '%'))")
    List<Country> getBySearch(@Param("symbol") String search);
}
