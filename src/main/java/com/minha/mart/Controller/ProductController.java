package com.minha.mart.Controller;

import com.minha.mart.DTO.ProductDTO;
import com.minha.mart.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품목록불러오기 (보여지기)
    @GetMapping("/event")
    public String findAll(Model model){
        List<ProductDTO> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "event";
    }
    @GetMapping("/different")
    public String findDifferent(Model model) {
        List<ProductDTO> productList2 = productService.findDifferentProducts();
        model.addAttribute("productList2", productList2);
        return "different";
    }

}
