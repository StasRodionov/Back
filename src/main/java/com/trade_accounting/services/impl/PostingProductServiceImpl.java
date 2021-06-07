package com.trade_accounting.services.impl;

import com.trade_accounting.models.PostingProduct;
import com.trade_accounting.models.dto.PostingProductDto;
import com.trade_accounting.repositories.PostingProductRepository;
import com.trade_accounting.services.interfaces.PostingProductService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostingProductServiceImpl implements PostingProductService {

    private final PostingProductRepository postingProductRepository;
    private final DtoMapper dtoMapper;

    public PostingProductServiceImpl(PostingProductRepository postingProductRepository, DtoMapper dtoMapper) {
        this.postingProductRepository = postingProductRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PostingProductDto> getAll() {
        return postingProductRepository.getAll().stream()
                .map(dtoMapper::postingProductToPostingProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public PostingProductDto getById(Long id) {
        PostingProduct postingProduct = postingProductRepository.getPostingById(id);
        return dtoMapper.postingProductToPostingProductDto(postingProduct);
    }

    @Transactional
    @Override
    public PostingProductDto create(PostingProductDto dto) {
        PostingProduct postingProductSave = postingProductRepository.save(dtoMapper.postingProductDtoToPostingProduct(dto));
        return dtoMapper.postingProductToPostingProductDto(postingProductSave);
    }

    @Transactional
    @Override
    public PostingProductDto update(PostingProductDto dto) {
        PostingProduct postingProductUpdate = postingProductRepository.save(dtoMapper.postingProductDtoToPostingProduct(dto));
        return dtoMapper.postingProductToPostingProductDto(postingProductUpdate);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        postingProductRepository.deleteById(id);
    }
}

