package com.minha.mart.Controller;

import com.minha.mart.DTO.ProductDTO;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/detail/{idx}")
    public String detail(Model model, @PathVariable("idx") Long idx){
        List<Integer> numbers = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        model.addAttribute("numbers", numbers);

        ProductEntity product = productService.getProductById(idx);
        model.addAttribute("product", product);

        return "detail";
    }


}
