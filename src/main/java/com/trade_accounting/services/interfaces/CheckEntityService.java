package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.dto.CompanyDto;
import com.trade_accounting.models.dto.EmployeeDto;

public interface CheckEntityService {
    void checkExistsUnitById(Long unitId);

    void checkExistsEmployeeById(Long employeeId);

    void checkExistsTypeOfContractorById(Long typeOfContractorId);

    void checkExistsProductById(Long productGroupId);

    void checkExistsProjectById(Long projectId);

    void checkExistsWarehouseById(Long warehouseId);

    void checkExistsAttributeOfCalculationObjectByID(Long attributeOfCalculationObjectId);

    void checkForBadEmployee(EmployeeDto employee);

    void checkExistsTaskById(Long taskId);

    void checkExistsTaskCommentById(Long taskCommentId);

    void checkExistsContractorGroupById(Long contractorGroupId);

    void checkExistsCurrencyById(Long currencyId);

    void checkExistCompanyById(Long companyId);

    void checkForBadCompany(CompanyDto company);

    void checkExistsRetailStoreById(Long RetailStoreId);

    void checkExistsProductGroupById(Long productGrouprId);

    void checkExistsTypeofPriceByID(Long typeOfPriceId);

    void checkExistsPositionById(Long positionId);

    void checkExistsTaxSystemById(Long taxSystemId);

    void checkExistsRoleById(Long roleId);

    void checkExistsPaymenById(Long paymenId);

    void checkExistsPaymentById(Long paymenId);

    void checkExistsInvoiceById(Long invoiceId);

    void checkExistsInvoiceProductById(Long invoiceProductId);

    void checkExistsLegalDetailById(Long legalDetailId);

    void checkExistsImageById(Long imageId);

    void checkExistsDepartmentById(Long departmentId);

    void checkExistsContractById(Long contractId);

    void checkExistsContractorById(Long contractorId);

    void checkExistsBankAccountById(Long bankAccountId);

    void checkExistsSupplierAccountById(Long supplierAccountId);

    void checkExistsTechnicalCardById(Long id);

    void checkExistsTechnicalCardGroupById(Long id);

    void checkExistsCorrectionById(Long correctionId);

    void checkExistsCorrectionProductById(Long correctionProduct);
}
