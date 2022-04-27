package com.trade_accounting.services.impl.purchases;

import com.trade_accounting.models.dto.purchases.PurchaseControlDto;
import com.trade_accounting.models.entity.purchases.PurchaseControl;
import com.trade_accounting.repositories.purchases.PurchaseControlRepository;
import com.trade_accounting.services.interfaces.company.CompanyService;
import com.trade_accounting.services.interfaces.company.ContractorService;
import com.trade_accounting.services.interfaces.purchases.PurchaseControlService;
import com.trade_accounting.services.interfaces.purchases.PurchaseCurrentBalanceService;
import com.trade_accounting.services.interfaces.purchases.PurchaseForecastService;
import com.trade_accounting.services.interfaces.warehouse.ProductService;
import com.trade_accounting.services.interfaces.warehouse.WarehouseService;
import com.trade_accounting.utils.mapper.purchases.PurchaseControlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseControlServiceImpl implements PurchaseControlService {
    private final PurchaseControlRepository purchaseControlRepository;
    private final PurchaseControlMapper purchaseControlMapper;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final PurchaseCurrentBalanceService purchaseCurrentBalanceService;
    private final PurchaseForecastService purchaseForecastService;
    private final ContractorService contractorService;
    private final CompanyService companyService;

    @Override
    public List<PurchaseControlDto> getAll() {
        return purchaseControlRepository.findAll().stream()
                .map(purchaseControlMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseControlDto getById(Long id) {
        return purchaseControlMapper.toDto(purchaseControlRepository.getOne(id));
    }

    @Override
    public PurchaseControlDto create(PurchaseControlDto dto) {
        PurchaseControl purchaseControl = purchaseControlMapper.toModel(dto);
        purchaseControlRepository.save(purchaseControl);
        return dto;
    }

    @Override
    public PurchaseControlDto update(PurchaseControlDto dto) {
        return create(dto);
    }

    @Override
    public void deleteById(Long id) {
        purchaseControlRepository.deleteById(id);
    }

    @Override
    public List<PurchaseControlDto> search(String searchTerm) {
        if ("null".equals(searchTerm) || searchTerm.isEmpty()) {
            List<PurchaseControl> allStore = purchaseControlRepository.findAll();
            return allStore.stream().map(purchaseControlMapper::toDto).collect(Collectors.toList());
        } else {
            List<PurchaseControl> list = purchaseControlRepository.search(searchTerm);
            return list.stream().map(purchaseControlMapper::toDto).collect(Collectors.toList());
        }
    }

    @Override
    public List<PurchaseControlDto> search(Specification<PurchaseControl> spec) {
        return executeSearch(purchaseControlRepository, purchaseControlMapper::toDto, spec);
    }

    /**
     * Input parameters:
     * 1 - "startDate"
     * 2 - "endDate"
     * 3 - "productId"
     * 4 - "available" ("Любой","Положительный","Отрицательный","Нулевой","Ненулевой","Ниже неснижаемого остатка")
     * 5 - "sold" ("Все","Только проданные","Только непроданные")
     * 6 - "remainder" ("Любой","Положительный","Отрицательный","Нулевой","Ненулевой","Ниже неснижаемого остатка")
     * 7 - "warehouseId"
     * 8 - "contractorId"
     * 9 - "companyId"
     */
    @Override
    public List<PurchaseControlDto> getAllForFilter(Map<String, String> map) {
        List<PurchaseControlDto> list = getAll();
        List<PurchaseControlDto> listResult = new ArrayList<>();

        list.stream()
                .filter(e -> checkDate(e, map.get("startDate"), map.get("endDate")))
                .filter(e -> checkProduct(e, map.get("productId")))
                .filter(e -> checkAvailable(e, map.get("available")))
                .filter(e -> checkSold(e, map.get("sold")))
                .filter(e -> checkRemainder(e, map.get("remainder")))
                .filter(e -> checkWarehouse(e, map.get("warehouseId")))
                .filter(e -> checkContractor(e, map.get("contractorId")))
                .filter(e -> checkCompany(e, map.get("companyId")))
                .forEach(listResult::add);
        return listResult;
    }

    //TODO Добавить обработку по времени (не только дата)
    private Boolean checkDate(PurchaseControlDto purchaseControlDto, String dateStart, String dateEnd) {
        boolean result = false;
        if (dateStart == null && dateEnd == null) {
            result = true;
        } else if (dateStart != null && dateEnd == null) {
            result = LocalDateTime.parse(purchaseControlDto.getDate())
                    .isAfter(LocalDateTime.parse(dateStart));
        } else if (dateStart == null && dateEnd != null) {
            result = LocalDateTime.parse(purchaseControlDto.getDate()).isBefore(LocalDateTime.parse(dateEnd));
        } else {
            result = LocalDateTime.parse(purchaseControlDto.getDate()).isAfter(LocalDateTime.parse(dateStart))
                    && LocalDateTime.parse(purchaseControlDto.getDate()).isBefore(LocalDateTime.parse(dateEnd));
        }
        return result;
    }

    private Boolean checkProduct(PurchaseControlDto purchaseControlDto, String requestIdProduct) {
        boolean result = false;
        if (requestIdProduct == null) {
            result = true;
        } else if (productService.getById(purchaseControlDto.getProductNameId()).getId() == Long.parseLong(requestIdProduct)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private Boolean checkAvailable(PurchaseControlDto purchaseControlDto, String requestAvailable) {
        boolean result = false;
        if (requestAvailable == null) {
            result = true;
        } else if (requestAvailable.equals("Ниже неснижаемого остатка")) {
            //TODO Реализовать неснижаемый остаток
        } else if (requestAvailable.equals("Ненулевой") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getProductsAvailableForOrder() > 0 &&
                        purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getProductsAvailableForOrder() < 0)) {
            result = true;
        } else if (requestAvailable.equals("Нулевой") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getProductsAvailableForOrder() == 0)) {
            result = true;
        } else if (requestAvailable.equals("Отрицательный") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getProductsAvailableForOrder() < 0)) {
            result = true;
        } else if (requestAvailable.equals("Положительный") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getProductsAvailableForOrder() > 0)) {
            result = true;
        } else if (requestAvailable.equals("Любой")) {
            result = true;
        }
        return result;
    }

    private Boolean checkSold(PurchaseControlDto purchaseControlDto, String requestSold) {
        boolean result = false;
        if (requestSold == null) {
            result = true;
        } else if (requestSold.equals("Только проданные") &&
                (purchaseControlDto.getProductQuantity() > 0)) {
            result = true;
        } else if (requestSold.equals("Только непроданные") &&
                (purchaseControlDto.getProductQuantity() < 0)) {
            result = false;
        } else if (requestSold.equals("Все")) {
            result = true;
        }
        return result;
    }

    private Boolean checkRemainder(PurchaseControlDto purchaseControlDto, String requestRemainder) {
        boolean result = false;
        if (requestRemainder == null) {
            result = true;
        } else if (requestRemainder.equals("Ниже неснижаемого остатка")) {
            //TODO Реализовать неснижаемый остаток
        } else if (requestRemainder.equals("Ненулевой") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getRestOfTheWarehouse() > 0 &&
                        purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getRestOfTheWarehouse() < 0)) {
            result = true;
        } else if (requestRemainder.equals("Нулевой") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getRestOfTheWarehouse() == 0)) {
            result = true;
        } else if (requestRemainder.equals("Отрицательный") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getRestOfTheWarehouse() < 0)) {
            result = true;
        } else if (requestRemainder.equals("Положительный") &&
                (purchaseCurrentBalanceService.getById(purchaseControlDto.getCurrentBalanceId()).getRestOfTheWarehouse() > 0)) {
            result = true;
        } else if (requestRemainder.equals("Любой")) {
            result = true;
        }
        return result;
    }

    private Boolean checkWarehouse(PurchaseControlDto purchaseControlDto, String requestIdWarehouse) {
        boolean result = false;
        if (requestIdWarehouse == null) {
            result = true;
        } else if (warehouseService.getById(purchaseControlDto.getWarehouseId()).getId() == Long.parseLong(requestIdWarehouse)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private Boolean checkContractor(PurchaseControlDto purchaseControlDto, String requestIdContractor) {
        boolean result = false;
        if (requestIdContractor == null) {
            result = true;
        } else if (contractorService.getById(purchaseControlDto.getContractorId()).getId() == Long.parseLong(requestIdContractor)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private Boolean checkCompany(PurchaseControlDto purchaseControlDto, String requestIdCompany) {
        boolean result = false;
        if (requestIdCompany == null) {
            result = true;
        } else if (companyService.getById(purchaseControlDto.getCompanyId()).getId() == Long.parseLong(requestIdCompany)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    //TODO Переделать нижний метод за место трех верхних методов согласно принципу полиморфизма
//    private Boolean checkParameters(AbstractService<T> service, PurchaseControlDto purchaseControlDto, String requestParam) {
//        boolean result = false;
//        if (requestParam == null) {
//            result = true;
//        } else if (service.getById(purchaseControlDto.getWarehouseId()).getId() == Long.parseLong(requestParam)) {
//            result = true;
//        } else {
//            result = false;
//        }
//        return result;
//    }
}
