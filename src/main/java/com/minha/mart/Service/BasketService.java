package com.minha.mart.Service;


import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Repository.BasketRepository;
import com.minha.mart.Repository.MemberRepository;
import com.minha.mart.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // Constructor for repositories
    public BasketService(BasketRepository basketRepository, MemberRepository memberRepository, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public void addToBasket(String userid, Long product) {
        Optional<MemberEntity> memberOptional = memberRepository.findByUserid(userid);
        ProductEntity productEntity = productRepository.findByIdx(product); // Non-optional return type

        if (!memberOptional.isPresent() || productEntity == null) {
            // Handle error: either user or product not found
            return;
        }

        MemberEntity member = memberOptional.get();

        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setMember(member);
        basketEntity.setProduct(productEntity);
        basketEntity.setAmount(1); // Set a fixed amount, for example, 1

        basketRepository.save(basketEntity);

    }
}
