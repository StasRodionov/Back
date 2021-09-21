package com.trade_accounting.services.impl;


import com.trade_accounting.models.File;
import com.trade_accounting.models.RetailStore;
import com.trade_accounting.models.dto.RetailStoreDto;
import com.trade_accounting.repositories.CompanyRepository;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.FileRepository;
import com.trade_accounting.repositories.RetailStoreRepository;
import com.trade_accounting.services.impl.Stubs.dto.RetailStoreDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.FileModelStubs;
import com.trade_accounting.services.impl.Stubs.model.RetailStoreModelStubs;
import com.trade_accounting.utils.mapper.RetailStoreMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceImplTest {

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    FileServiceImpl fileService;

    @Test
    void getById() {
        when(fileRepository.findById(anyLong())).thenReturn(java.util.Optional.of(FileModelStubs.getFiles(1L)));

        File file = fileService.getById(1L);
        assertEquals(1, file.getId());
    }

    @Test
    void create() throws IOException {
        when(fileRepository.save(any(File.class))).thenReturn(FileModelStubs.getFiles(1L));
        String name = FileModelStubs.getFiles(1L).getName();
        InputStream content=  fileService.download("2175d55e974b95f88d3894247ab55cf1").getInputStream();
        MultipartFile result = new MockMultipartFile(name, content);

        File file = fileService.create(result);
        assertEquals(1,file.getId());
        verify(fileRepository).save(any(File.class));
    }

    @Test
    void delete() {
        fileRepository.deleteById(anyLong());
        verify(fileRepository).deleteById(anyLong());
    }

}
