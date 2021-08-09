//package com.trade_accounting.config.init;
//
//import com.trade_accounting.models.TypeOfInvoice;
//import com.trade_accounting.models.dto.CompanyDto;
//import com.trade_accounting.models.dto.ContractorDto;
//import com.trade_accounting.models.dto.InvoiceDto;
//import com.trade_accounting.models.dto.WarehouseDto;
//import com.trade_accounting.services.interfaces.CompanyService;
//import com.trade_accounting.services.interfaces.ContractorService;
//import com.trade_accounting.services.interfaces.InvoiceService;
//import com.trade_accounting.services.interfaces.WarehouseService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Locale;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class InvoiceInit extends InitData {
//    private final CompanyService companyService;
//    private final ContractorService contractorService;
//    private final WarehouseService warehouseService;
//    private final InvoiceService invoiceService;
//
//    @Override
//    void init() {
//        initInvoices();
//    }
//
//    public void initInvoices() {
//        List<CompanyDto> companyDtos = companyService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<ContractorDto> contractorDtos = contractorService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<WarehouseDto> warehouseDtos = warehouseService.getAll().stream().limit(3).collect(Collectors.toList());
//        List<String> typeOfInvoices = List.of(TypeOfInvoice.EXPENSE.name(), TypeOfInvoice.RECEIPT.name());
//
//        int i = 0;
//        for (CompanyDto companyDto : companyDtos) {
//            for (ContractorDto contractorDto : contractorDtos) {
//                for (WarehouseDto warehouseDto : warehouseDtos) {
//                    invoiceService.create(new InvoiceDto(
//                            null,
//                            "Комментарий " + i++,
//                            LocalDateTime.now().toString(),
//                            typeOfInvoices.get(randomInt(0, 1)),
//                            companyDto.getId(),
//                            contractorDto.getId(),
//                            warehouseDto.getId(),
//                            false));
//                }
//            }
//        }
//    }
//}
////        for (long i = 1L; i <= 14; i += 3){
////                    invoiceService.create(InvoiceDto.builder()
////                            .id(null)
////                            .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.US)))
////                            .typeOfInvoice(TypeOfInvoice.EXPENSE.toString())
////                            .companyId(i)
////                            .contractorId(i)
////                            .warehouseId(i)
////                            .isSpend(false)
////                            .comment("Комментарий " + i)
////                            .build()
////                    );
////
////                }
////            }
//
//
////}
