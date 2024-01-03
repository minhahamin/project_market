package com.minha.mart.Service;


import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BasketService {

    private final BasketRepository basketRepository;

    // 생성자 주입을 통한 BasketRepository 초기화
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public BasketEntity addProductToBasket(BasketEntity basket) {
        return basketRepository.save(basket);
    }
    public List<BasketEntity> getBasketByUserid(String userid) {
        return basketRepository.findByMember_Userid(userid);
    }

    public List<BasketEntity> getBasketItems() {
        // Retrieve the basket items from the repository
        List<BasketEntity> basketItems = basketRepository.findAll();

        // Check if the basketItems is null or empty
        if (basketItems.isEmpty()) {
            // Handle the case where there are no basket items
            // You can return an empty list or throw an exception, depending on your requirements
            // For example, you can return an empty list:
            return Collections.emptyList();
        }

        return basketItems;
    }

    public void deleteBasketItem(Long idx) {
        Optional<BasketEntity> basketItem = basketRepository.findById(idx);
        basketItem.ifPresent(basketRepository::delete);
    }
    public Optional<BasketEntity> updateBasketItem(Long idx, int amount) {
        Optional<BasketEntity> basketItem = basketRepository.findById(idx);
        basketItem.ifPresent(item -> {
            item.setAmount(amount);
            basketRepository.save(item);
        });
        return basketItem;
    }
}
