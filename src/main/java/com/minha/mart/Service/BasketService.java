package com.minha.mart.Service;


import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Repository.BasketRepository;
import com.minha.mart.Repository.MemberRepository;
import com.minha.mart.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Long addToBasket(String userid, Long product, int amount) {
        Optional<MemberEntity> memberOptional = memberRepository.findByUserid(userid);
        ProductEntity productEntity = productRepository.findByIdx(product);

        if (!memberOptional.isPresent() || productEntity == null) {

            throw new RuntimeException("사용자 또는 상품을 찾을 수 없습니다.");
        }

        MemberEntity member = memberOptional.get();

        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setMember(member);
        basketEntity.setProduct(productEntity);
        basketEntity.setAmount(amount);

        basketRepository.save(basketEntity);

        return member.getIdx();


    }
    public List<BasketDTO> getBasketDetails(String userid) {
        Set<BasketEntity> baskets = basketRepository.findByMember_Userid(userid);
        return BasketEntity.getBasketDetails(baskets);
    }
    public List<BasketDTO> getBasketListForMember(MemberEntity member) {
        return BasketEntity.getBasketListForMember(member);
    }

    @Transactional
    public void updateBasketAmount(Long idx, int amount) {
        BasketEntity basketEntity = basketRepository.findById(idx)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        // 여기에 필요한 경우 추가적인 로직을 추가할 수 있습니다.

        basketEntity.setAmount(amount);
    }

    public List<BasketDTO> getBasketsForMemberId(String userid){
        Optional<MemberEntity> memberOptional = memberRepository.findByUserid(userid);

        MemberEntity member = memberOptional.orElseThrow(() -> new RuntimeException("Member not found"));

        return BasketEntity.getBasketListForMember(member);
    }
    @Transactional
    public void deleteItemById(Long idx) {
        basketRepository.deleteById(idx);
    }
    @Transactional
    public void deleteItemsByIndices(List<Long> itemIndices) {
        basketRepository.deleteByIdIn(itemIndices);
    }
}
