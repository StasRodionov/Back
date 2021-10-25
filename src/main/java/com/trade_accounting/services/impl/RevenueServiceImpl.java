package com.trade_accounting.services.impl;

import com.trade_accounting.models.Revenue;
import com.trade_accounting.models.dto.RevenueDto;
import com.trade_accounting.repositories.RevenueRepository;
import com.trade_accounting.services.interfaces.RevenueService;
import com.trade_accounting.utils.mapper.RevenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {

	private final RevenueRepository revenueRepository;
	private final RevenueMapper revenueMapper;

	@Override
	public List<RevenueDto> getAll() {
		return revenueRepository.findAll().stream()
				.map(revenueMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public RevenueDto getById(Long id) {
		return revenueMapper.toDto(revenueRepository.getOne(id));
	}

	@Override
	public RevenueDto create(RevenueDto dto) {
		Revenue revenue = revenueMapper.toModel(dto);
		return revenueMapper.toDto(revenueRepository.save(revenue));
	}

	@Override
	public RevenueDto update(RevenueDto dto) {
		return create(dto);
	}

	@Override
	public void deleteById(Long id) {
		revenueRepository.deleteById(id);
	}
}
