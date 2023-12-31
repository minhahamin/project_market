package com.minha.mart.Service;

import com.minha.mart.DTO.ProductDTO;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(ProductEntity productEntity : productEntityList){
            productDTOList.add(ProductDTO.toProductDTO(productEntity));
        }
        return productDTOList;
    }

    public List<ProductDTO> findDifferentProducts() {
        List<ProductEntity> productEntityList = productRepository.findByProCategory2("차별화상품");
        return productEntityList.stream()
                .map(ProductDTO::toProductDTO)
                .collect(Collectors.toList());
    }
}
