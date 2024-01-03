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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public String addProductToBasket(@ModelAttribute BasketDTO basketDTO, RedirectAttributes redirectAttributes){
        // 여기서 memberService와 productService를 사용하여 MemberEntity와 ProductEntity를 조회합니다.
        MemberEntity member = memberService.findMemberByUserId(basketDTO.getUserid());
        ProductEntity product = productService.findProductById(basketDTO.getProduct());

        // DTO에서 Entity로 변환
        BasketEntity basket = BasketEntity.toBasketEntity(basketDTO, member, product);

        // 추가된 BasketEntity를 저장
        BasketEntity addedBasket = basketService.addProductToBasket(basket);

        // addedBasket에서 amount 필드를 사용하는 예시
        int basketAmount = addedBasket.getAmount();

        // 리다이렉트 시에 데이터 전달
        redirectAttributes.addFlashAttribute("addedBasket", addedBasket);

        // 클라이언트를 'basket' 페이지로 리다이렉트
        return "basket";
    }


    // 2. 장바구니 목록
    @GetMapping("/basket")
    public String getBasketList(Model model) {
        // Retrieve the basket items from your service
        List<BasketEntity> basketItems = basketService.getBasketItems();

        // Add the basket items to the model
        model.addAttribute("basketItems", basketItems);

        // Return the Thymeleaf template for rendering
        return "basket";
    }

    // 3. 장바구니 삭제
    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<?> deleteBasketItem(@PathVariable Long idx) {
        basketService.deleteBasketItem(idx);
        return ResponseEntity.ok().build();
    }

    // 4. 장바구니 수정
    @PatchMapping("/update/{basketId}")
    public ResponseEntity<BasketEntity> updateBasketItem(@PathVariable Long idx, @RequestParam int amount) {
        Optional<BasketEntity> updatedItem = basketService.updateBasketItem(idx, amount);
        return updatedItem
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
