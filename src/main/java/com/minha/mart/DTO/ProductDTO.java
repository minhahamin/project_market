package com.minha.mart.DTO;


import com.minha.mart.Entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    public Long idx ;
    public String pro_name ;
    public int pro_price;
    public String pro_category;
    public String pro_category2;
    public String pro_describe;
    public String pro_photo ;

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdx(productEntity.getIdx());
        productDTO.setPro_name(productEntity.getProName());
        productDTO.setPro_price(productEntity.getProPrice());
        productDTO.setPro_category(productEntity.getProCategory());
        productDTO.setPro_category2(productEntity.getProCategory2());
        productDTO.setPro_describe(productEntity.getProDescribe());
        productDTO.setPro_photo(productEntity.getProPhoto());
        return productDTO;

    }
}
