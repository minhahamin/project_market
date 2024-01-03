package com.minha.mart.Repository;

import com.minha.mart.Entity.ProductEntity;

import java.util.Optional;

public interface ProductServiceImpl {
    ProductEntity getProductById(Long idx);

}
