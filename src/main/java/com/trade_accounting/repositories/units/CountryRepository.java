package com.trade_accounting.repositories.units;

import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.entity.units.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {

    @Query("select new com.trade_accounting.models.dto.units.CountryDto(" +
            "country.id, " +
            "country.type, " +
            "country.shortName," +
            "country.fullName, " +
            "country.digitCode, " +
            "country.twoLetterCode, " +
            "country.threeLetterCode) from Country country")
    List<CountryDto> getAll();

    @Query("select new com.trade_accounting.models.dto.units.CountryDto(" +
            "country.id, " +
            "country.type, " +
            "country.shortName," +
            "country.fullName, " +
            "country.digitCode, " +
            "country.twoLetterCode, " +
            "country.threeLetterCode) from Country country " +
            "where country.id = :id")
    CountryDto getById(@Param("id") Long id);

    @Query("from Country c " +
            "where lower ( concat(c.id, ' ', c.type, ' ', c.shortName, ' ', c.fullName, ' ', c.digitCode, ' ', c.twoLetterCode, ' ', c.threeLetterCode)) " +
            "like lower (concat('%', :symbols, '%'))")
    List<Country> getBySearch(@Param("symbols") String search);
}
