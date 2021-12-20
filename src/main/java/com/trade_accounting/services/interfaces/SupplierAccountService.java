package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.SupplierAccount;
import com.trade_accounting.models.TypeOfInvoice;
import com.trade_accounting.models.dto.SupplierAccountDto;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SupplierAccountService extends AbstractService<SupplierAccountDto>,
        SearchableService<SupplierAccount, SupplierAccountDto> {

    List<SupplierAccountDto> searchByString(String nameFilter);

    List<SupplierAccountDto> findBySearchAndTypeOfInvoice(String search, TypeOfInvoice typeOfInvoice);

    @Transactional
    default List<SupplierAccountDto> getAll(String typeOfInvoice) {
        return search((root, query, builder)
                -> builder.equal(root.get("typeOfInvoice"), TypeOfInvoice.valueOf(typeOfInvoice)));
    }

    void moveToRecyclebin(long id);
    void restoreFromRecyclebin(long id);

}
