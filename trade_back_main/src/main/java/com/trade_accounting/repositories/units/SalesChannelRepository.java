package com.trade_accounting.repositories.units;

import com.trade_accounting.models.dto.units.SalesChannelDto;
import com.trade_accounting.models.entity.units.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel, Long>, JpaSpecificationExecutor<SalesChannel> {

    @Query("select new com.trade_accounting.models.dto.units.SalesChannelDto(" +
            "salesChannel.id, " +
            "salesChannel.name, " +
            "salesChannel.type," +
            "salesChannel.description," +
            "salesChannel.generalAccess," +
            "salesChannel.departmentOwner," +
            "salesChannel.employeeOwner," +
            "salesChannel.dateOfChange," +
            "salesChannel.employeeChange" +
            ") from SalesChannel salesChannel")
    List<SalesChannelDto> getAll();

    @Query("select new com.trade_accounting.models.dto.units.SalesChannelDto(sc.id, sc.name, sc.type, sc.description, sc.generalAccess, sc.departmentOwner, sc.employeeOwner, sc.dateOfChange, sc.employeeChange) from SalesChannel sc where sc.id = :id")
    SalesChannelDto getById(@Param("id") Long id);

    @Query("from SalesChannel sc " +
    "where lower ( concat(sc.id, ' ', sc.name, ' ', sc.type, ' ', sc.description, ' ', sc.generalAccess, ' ', sc.departmentOwner, ' ', sc.employeeOwner, ' ', sc.dateOfChange, ' ', sc.employeeChange)) " +
    "like lower (concat('%', :symbols, '$')) ")
    List<SalesChannel> getBySearch(@Param("symbols") String search);
}
