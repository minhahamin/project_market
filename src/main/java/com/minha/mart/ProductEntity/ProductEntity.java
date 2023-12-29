package com.minha.mart.ProductEntity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table (name = "tb_product")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "pro_name", length = 200)
    private String proName;

    @Column(name = "pro_price")
    private int proPrice;

    @Column(name = "pro_category", length = 200)
    private String proCategory;

    @Column(name = "pro_category2", length = 200)
    private String proCategory2;

    @Column(name = "pro_describe", length = 500)
    private String proDescribe;

    @Column(name = "pro_photo", length = 500)
    private String proPhoto;

}
