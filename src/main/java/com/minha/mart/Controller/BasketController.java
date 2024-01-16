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
import java.util.*;

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
    public ModelAndView addToBasket(@RequestParam String userid, @RequestParam Long product, @RequestParam int amount) {
        ModelAndView modelAndView = new ModelAndView();

        // basketService.addToBasket 메서드를 호출하여 장바구니에 아이템 추가
        // 이 과정에서 사용자의 고유 식별자 idx를 얻거나 계산해야 할 수도 있음
       Long idx = basketService.addToBasket(userid, product, amount);

        // 모델에 idx 값을 추가
        modelAndView.addObject("idx", idx);
        // 모델에 userid 값을 추가
        modelAndView.addObject("userid", userid);

        // 리다이렉트할 때 idx 값을 URL 경로에 포함시킴
        modelAndView.setViewName("redirect:/basket/basketList/{userid}");
        return modelAndView;
    }





    // 2. 장바구니 목록
  @GetMapping("/basketList/{userid}")
    public String getBasketsByMemberId(@PathVariable String userid, Model model) {
        List<BasketDTO> basketList = basketService.getBasketsForMemberId(userid);

       // 중복 제거를 위해 HashSet 사용
        Set<BasketDTO> uniqueBaskets = new HashSet<>(basketList);

        // 다시 리스트로 변환
        List<BasketDTO> uniqueBasketList = new ArrayList<>(uniqueBaskets);

        model.addAttribute("basketList", basketList);
        return "basketList"; // 장바구니 목록을 보여주는 뷰의 이름
    }




    // 3. 장바구니 삭제
    @PostMapping("/delete/{idx}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteItem(@PathVariable Long idx) {
        // Perform delete operation in the database using the idx
        basketService.deleteItemById(idx);

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }


    // 4. 장바구니 전체 삭제
    @PostMapping("/delete/all")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteAllItems(@RequestBody Map<String, List<Long>> requestBody) {
        List<Long> itemIndices = requestBody.get("itemIndices");

        // Perform delete operation in the database using the item indices
        basketService.deleteItemsByIndices(itemIndices);

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }



    // 4. 장바구니 수정
  /*  @PostMapping("/update")
    public String updateBasket(@ModelAttribute BasketDTO basketDTO) {
        basketService.updateBasket(basketDTO);
        return "redirect:/basketList";
    }*/

}
