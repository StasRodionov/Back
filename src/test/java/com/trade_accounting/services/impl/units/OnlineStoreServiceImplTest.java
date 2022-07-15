package com.trade_accounting.services.impl.units;

import com.trade_accounting.Stubs.dto.units.OnlineStoreStubs;
import com.trade_accounting.models.dto.units.CountryDto;
import com.trade_accounting.models.dto.units.OnlineStoreDto;
import com.trade_accounting.repositories.units.OnlineStoreRepository;
import com.trade_accounting.utils.mapper.units.OnlineStoreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class OnlineStoreServiceImplTest {

    @Mock
    private OnlineStoreRepository onlineStoreRepository;
    @InjectMocks
    private OnlineStoreServiceImpl onlineStoreService;
    @Spy
    private OnlineStoreMapper onlineStoreMapper;

    @Test
    public void getAll_testReturnOnlineStoreDtoList() {
        when(onlineStoreRepository.getAll()).thenReturn(Stream.of(OnlineStoreStubs.getOnlineStoreDto(1L)).collect(Collectors.toList()));
        List<OnlineStoreDto> dtos = onlineStoreService.getAll();

        assertNotNull(dtos, "Failure - expected OnlineStoreDto list not null");
        assertEquals(1, dtos.size(), "Failure - expected countryDtoList size is 1");

        for (OnlineStoreDto onlineStoreDto : dtos) {
            onlineStoreDtoIsCorrectlyInited(onlineStoreDto);
        }
    }

    @Test
    public void getAll_testReturnEmptyList() {
        when(onlineStoreRepository.getAll()).thenReturn(new ArrayList<>());

        List<OnlineStoreDto> dtos = onlineStoreService.getAll();

        assertNotNull(dtos, "Failure - expected OnlineStoreDto list not null");
        assertEquals(0, dtos.size(), "Failure - expected OnlineStoreDto size equals 0");
    }

    @Test
    public void create_testSuccessfullCreation() {
        OnlineStoreDto dto = onlineStoreService.create(OnlineStoreStubs.getOnlineStoreDto(1L));

        assertEquals(dto, onlineStoreService.create(dto));
    }

    @Test
    public void update_testSuccessfullUpdate() {
        OnlineStoreDto dto = onlineStoreService.update(OnlineStoreStubs.getOnlineStoreDto(1L));

        assertEquals(dto, onlineStoreService.create(dto));
    }

    @Test
    public void getById_testReturnById() {
        OnlineStoreDto dto = OnlineStoreStubs.getOnlineStoreDto(1L);

        when(onlineStoreRepository.getById(anyLong())).thenReturn(dto);

        onlineStoreDtoIsCorrectlyInited(onlineStoreService.getById(1L));
    }

    @Test
    public void deleteById_testSuccessfulDelete() {
        onlineStoreService.deleteById(1L);

        verify(onlineStoreRepository).deleteById(anyLong());
    }

    private void onlineStoreDtoIsCorrectlyInited(OnlineStoreDto onlineStoreDto) {
        assertNotNull(onlineStoreDto, "Failure - onlineStoreDto pass failure");
        assertNotNull(onlineStoreDto.getId(), "Failure - onlineStoreDto 'id' failure");
        assertNotNull(onlineStoreDto.getName(), "Failure - onlineStoreDto 'name' failure");
    }
}
