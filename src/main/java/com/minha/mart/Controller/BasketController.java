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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView addToBasket(@RequestParam String userid, @RequestParam Long product) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            basketService.addToBasket(userid, product);
            modelAndView.addObject("message", "Product added to basket successfully.");
            modelAndView.setViewName("redirect:/basket/basketList");
        } catch (Exception e) {
            modelAndView.addObject("error", "Error adding product to basket: " + e.getMessage());
            modelAndView.setViewName("redirect:/errorPage"); // 에러 페이지 또는 적절한 경로로 변경
        }
        return modelAndView;
    }




    // 2. 장바구니 목록
    @GetMapping("/basketList")
    public String viewBasketList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 로그인하지 않았거나 인증되지 않은 경우
        if (authentication == null || !authentication.isAuthenticated()) {
            // 예: 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        String userId = authentication.getName(); // 로그인한 사용자의 ID를 가져옴
        System.out.println("아이디 :" + userId);

        List<BasketDTO> basketList = basketService.getBasketDetails(userId);
        model.addAttribute("basketList", basketList);
        return "basketList";
    }


    // 3. 장바구니 삭제


    // 4. 장바구니 수정
    @PostMapping("/update")
    public String updateBasket(@ModelAttribute BasketDTO basketDTO) {
        basketService.updateBasket(basketDTO);
        return "redirect:/basketList";
    }

}
