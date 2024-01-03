package com.minha.mart.Controller;

import com.minha.mart.DTO.MemberDTO;
import com.minha.mart.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/join")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/member/join")
    public String join(@ModelAttribute MemberDTO memberDTO, Model model){
        try {
            memberService.join(memberDTO);
        } catch (ParseException e) {
            model.addAttribute("error", "날짜 형식이 올바르지 않습니다. yyyy-MM-dd 형식으로 입력해주세요.");
            e.printStackTrace(); // 로그 기록
            return "join"; // 오류가 있는 경우 다시 회원가입 페이지로 이동
        }
        model.addAttribute("message", "회원가입에 성공했습니다 :)");
        return "login";  // 회원가입 하면 로그인화면으로 이동
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model){

        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            // login 성공
            session.setAttribute("loginID", loginResult.getUserid());
            model.addAttribute("message", "로그인 성공 :)");
            return "main";
        } else{
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "main";
    }

    @GetMapping("/member/mypage")
    public String mypageForm() { return "mypage";}

    @GetMapping("/member/infoupdate")
    public String infoupdateForm (Model model, HttpSession session) throws ParseException {
        String userId = (String) session.getAttribute("loginID");
        MemberDTO memberDTO = memberService.infoupdateForm(userId);
        model.addAttribute("updateMember", memberDTO);
        return "infoupdate";
    }

    @PostMapping("/member/infoupdate/{idx}")
    public String infoupdate(@PathVariable Long idx, @ModelAttribute MemberDTO memberDTO) {
        memberService.infoupdate(idx, memberDTO);
        return "redirect:/member/mypage";
    }



}
