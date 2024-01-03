package com.minha.mart.Controller;

import com.minha.mart.DTO.BasketDTO;
import com.minha.mart.DTO.MemberDTO;
import com.minha.mart.DTO.ProductDTO;
import com.minha.mart.Entity.BasketEntity;
import com.minha.mart.Entity.MemberEntity;
import com.minha.mart.Entity.ProductEntity;
import com.minha.mart.Repository.BasketRepository;
import com.minha.mart.Service.BasketService;
import com.minha.mart.Service.MemberService;
import com.minha.mart.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
public class BasketController {

    // 생성자 주입
    private final BasketService basketService;
    private MemberService memberService;
    private ProductService productService;

    public BasketController(BasketService basketService, MemberService memberService, ProductService productService) {
        this.basketService = basketService;
        this.memberService = memberService;
        this.productService = productService;
    }

    // 1. 장바구니 추가
    @PostMapping("/add")
    public ResponseEntity<?> addToBasket(@RequestParam String userid, @RequestParam Long product) {
        try {
            basketService.addToBasket(userid, product);
            return ResponseEntity.ok().body("Product added to basket successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding product to basket: " + e.getMessage());
        }
    }


    // 2. 장바구니 목록


    // 3. 장바구니 삭제


    // 4. 장바구니 수정


}
