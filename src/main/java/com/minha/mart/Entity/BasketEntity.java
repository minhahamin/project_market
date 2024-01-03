package com.minha.mart.Entity;


import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.DTO.MemberDTO;
import com.minha.mart.DTO.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

    public static BasketEntity toBasketEntity(BasketDTO basketDTO, MemberEntity member, ProductEntity product) {
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setIdx(basketDTO.getIdx());
        basketEntity.setAmount(basketDTO.getAmount());

        // MemberEntity와 ProductEntity는 별도로 제공되어야 함
        // MemberEntity와 ProductEntity 생성
        basketEntity.setMember(member);
        basketEntity.setProduct(product);



        return basketEntity;
    }



   /* @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private MemberEntity userid;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idx", insertable = true, updatable = true)
    private ProductEntity product;*/


}
