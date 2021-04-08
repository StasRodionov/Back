package com.trade_accounting.repositories;

import com.trade_accounting.models.Contractor;
import com.trade_accounting.models.dto.ContractorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long>, JpaSpecificationExecutor<Contractor> {

    @Query
            ("select new com.trade_accounting.models.dto.ContractorDto(" +
            "c.id," +
            "c.name," +
            "c.inn," +
            "c.sortNumber," +
            "c.phone," +
            "c.fax," +
            "c.email," +
            "c.address," +
            "c.commentToAddress," +
            "c.comment," +
            "c.contractorGroup.name,"+
            "c.typeOfContractor.name,"+
            "c.typeOfPrice.name,"+
            "c.bankAccounts.size,"+
            "c.legalDetail.inn"+
            ") from Contractor c " +
                    "LEFT OUTER JOIN ContractorGroup AS cg " +
                    "ON c.contractorGroup.id =  cg.id  " +
                    "LEFT OUTER JOIN TypeOfContractor as toc " +
                    "ON c.typeOfContractor.id  =  toc.id " +
                    "LEFT OUTER JOIN TypeOfPrice as top " +
                    "ON c.typeOfPrice.id  =  top.id  " +
                    "LEFT OUTER JOIN LegalDetail as ld " +
                    "ON c.legalDetail.id  =  ld.id  "
//                    "LEFT OUTER JOIN ContractorGroup AS cg " +
//                    "ON c.contractorGroup.name =  cg.name " +
//                    "LEFT OUTER JOIN TypeOfContractor as toc " +
//                    "ON c.typeOfContractor.name  =  toc.name " +
//                    "LEFT OUTER JOIN TypeOfPrice as top " +
//                    "ON c.typeOfPrice.name  =  top.name  " +
//                    "LEFT OUTER JOIN LegalDetail as ld " +
//                    "ON c.legalDetail.inn  =  ld.inn  "

            )
    List<ContractorDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "c.id," +
            "c.name," +
            "c.inn," +
            "c.sortNumber," +
            "c.phone," +
            "c.fax," +
            "c.email," +
            "c.address," +
            "c.commentToAddress," +
            "c.comment," +
            " c.contractorGroup.name ,"+
            "c.typeOfContractor.name,"+
            "c.typeOfPrice.name,"+
            "c.bankAccounts.size,"+
            "c.legalDetail.inn,"+
            "c.contractorGroup.id,"+
            "c.typeOfContractor.id,"+
            "c.typeOfPrice.id,"+
            "c.legalDetail.id"+
            ") from Contractor c LEFT OUTER JOIN ContractorGroup AS cg " +
            "ON c.contractorGroup.id =  cg.id " +
            "LEFT OUTER JOIN TypeOfContractor as toc " +
            "ON c.typeOfContractor.id =  toc.id " +
            "LEFT OUTER JOIN TypeOfPrice as top " +
            "ON c.typeOfPrice.id =  top.id " +
//            "LEFT OUTER JOIN BankAccount as ba " +
//            "ON c.bankAccounts.size =  0 " +
            "LEFT OUTER JOIN LegalDetail as ld " +
            "ON c.legalDetail.id =  ld.id " +
            " where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "      or c.inn like concat('%', :searchTerm, '%')" +
            "      or c.phone like concat('%',  :searchTerm, '%')" +
            "      or c.comment like concat('%', :searchTerm, '%')"
    )
    List<ContractorDto> search(@Param("searchTerm") String searchTerm);

//    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
//            "e.id," +
//            "e.name," +
//            "e.inn," +
//            "e.sortNumber," +
//            "e.phone," +
//            "e.fax," +
//            "e.email," +
//            "e.address," +
//            "e.commentToAddress," +
//            "e.comment," +
//            "e.contractorGroup.name,"+
//            "e.typeOfContractor.name,"+
//            "e.typeOfPrice.name,"+
//            "e.bankAccounts.size,"+
//            "e.legalDetail.inn"+
//            ") from Contractor e LEFT OUTER JOIN ContractorGroup AS cg " +
//            "ON e.contractorGroup.id =  cg.id " +
//            "LEFT OUTER JOIN TypeOfContractor as toc " +
//            "ON e.typeOfContractor.id =  toc.id " +
//            "LEFT OUTER JOIN TypeOfPrice as top " +
//            "ON e.typeOfPrice.id =  top.id " +
////            "LEFT OUTER JOIN BankAccount as ba " +
////            "ON e.bankAccounts.size =  0 " +
//            "LEFT OUTER JOIN LegalDetail as ld " +
//            "ON e.legalDetail.id =  ld.id " +
//            " where e.id = :id")
//    ContractorDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ContractorDto(" +
            "p.contractor.id," +
            "p.contractor.name," +
            "p.contractor.inn," +
            "p.contractor.sortNumber," +
            "p.contractor.phone," +
            "p.contractor.fax," +
            "p.contractor.email," +
            "p.contractor.address," +
            "p.contractor.commentToAddress," +
            "p.contractor.comment," +
            "p.contractor.typeOfPrice.id " +
            ") from Product p where p.id = :id")
    ContractorDto getContractorById(@Param("id") Long id);

}
