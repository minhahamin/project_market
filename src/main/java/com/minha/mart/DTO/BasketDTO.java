package com.minha.mart.DTO;

import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BasketDTO {
    private Long idx;
    private String userid;
    private Long product;  // 상품 idx
    private int amount;   // 구매수량

    private String username; // 사용자 이름
    private String pro_name; // 상품 이름
    private int pro_price; // 상품 단가
    private String pro_photo;
    private int money; // 상품 가격

    private int sumMoney;

    public static BasketDTO toBasketDTO (BasketEntity basketEntity){
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setIdx(basketEntity.getIdx());
        basketDTO.setUserid(basketEntity.getMember().getUserid());
        basketDTO.setProduct(basketEntity.getProduct().getIdx());
        basketDTO.setAmount(basketEntity.getAmount());

        return basketDTO;
    }


}
