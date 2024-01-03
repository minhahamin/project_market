package com.minha.mart.Service;

import com.minha.mart.DTO.ProductDTO;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Repository.ProductRepository;
import com.minha.mart.Repository.ProductServiceImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService implements ProductServiceImpl {

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

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null); // Or handle NotFoundException
    }
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity findProductById(Long product) {
        // productId를 사용하여 ProductEntity를 조회
        return productRepository.findById(product)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + product));
    }
}
