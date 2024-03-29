package com.minha.mart.Entity;


import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.DTO.MemberDTO;
import com.minha.mart.DTO.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "tb_basket")
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private int amount;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idx")
    private ProductEntity product;

   /* public static BasketEntity toBasketEntity(BasketDTO basketDTO, MemberEntity member, ProductEntity product) {
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setIdx(basketDTO.getIdx());
        basketEntity.setAmount(basketDTO.getAmount());

        // MemberEntity와 ProductEntity는 별도로 제공되어야 함
        // MemberEntity와 ProductEntity 생성
        basketEntity.setMember(member);
        basketEntity.setProduct(product);



        return basketEntity;
    }
*/

    // 장바구니 상세 정보를 가져오는 메서드
    public static List<BasketDTO> getBasketDetails(Set<BasketEntity> baskets) {
        List<BasketDTO> basketDetails = new ArrayList<>();

        for (BasketEntity basket : baskets) {
            BasketDTO details = new BasketDTO();
            details.setPro_name(basket.getProduct().getProName());
            details.setAmount(basket.getAmount());
            details.setPro_price(basket.getProduct().getProPrice());
            // 필요한 다른 상세 정보 추가
            basketDetails.add(details);
        }

        return basketDetails;

    }
    public static List<BasketDTO> getBasketListForMember(MemberEntity member) {
        return member.getBaskets().stream().map(basket -> {
            BasketDTO basketDTO = new BasketDTO();
            basketDTO.setIdx(basket.getIdx());
            basketDTO.setUserid(basket.getMember().getUserid());
            basketDTO.setPro_name(basket.getProduct().getProName());
            basketDTO.setAmount(basket.getAmount());
            basketDTO.setPro_price(basket.getProduct().getProPrice());
            basketDTO.setPro_photo(basket.getProduct().getProPhoto());
            basketDTO.setSumMoney(basket.getProduct().getProPrice() * basket.getAmount());
            basketDTO.setUsername(basket.getMember().getUsername());
            // 필요한 다른 상세 정보도 여기에 추가할 수 있습니다.
            return basketDTO;
        }).collect(Collectors.toList());
    }
}