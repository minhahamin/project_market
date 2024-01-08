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
import java.util.Set;
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
    public List<BasketDTO> getBasketDetails(String userid) {
        Set<BasketEntity> baskets = basketRepository.findByMember_Userid(userid);
        return BasketEntity.getBasketDetails(baskets);
    }
    public void updateBasket(BasketDTO basketDTO) {
        // DTO로부터 Entity를 생성하거나 업데이트
        // 예시로, 여기에서는 기존 BasketEntity를 찾아 업데이트하는 로직을 구현
        BasketEntity basket = basketRepository.findById(basketDTO.getIdx())
                .orElseThrow(() -> new IllegalArgumentException("Invalid basket Id:" + basketDTO.getIdx()));

        basket.setAmount(basketDTO.getAmount());
        // 필요한 다른 필드 업데이트
        basketRepository.save(basket);
    }
}
