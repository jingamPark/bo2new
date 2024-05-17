package com.example.bo2.controller;


import com.example.bo2.dto.SecUserDTO;
import com.example.bo2.entity.SecUser;
import com.example.bo2.service.SecUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
public class SecUserController {



    private final SecUserService secUserService;

    //로그인페이지 //post는 시큐리티가
    @GetMapping("/login")
    public void login() {

    }

    @GetMapping("/signup")
    public void signup(SecUserDTO secUserDTO) {

    }

    @PostMapping("/signup")
    public String signupPost(@Valid SecUserDTO secUserDTO,
                             BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "/user/signup";
        }
        //1번 비밀번호와 2번 1비밀번호의 일치여부확인
        if ( !secUserDTO.getPassword1().equals(secUserDTO.getPassword2() )) {

            log.info(secUserDTO);

            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "/user/signup";
        }
        try {
            secUserService
                    .create(secUserDTO.getUsername(), secUserDTO.getEmail(), secUserDTO.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미등록된 사용자입니다.");
            return "/user/signup";
        }


        return "redirect:/board/list";
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping("/idcheck")
    public void idcheck(SecUserDTO secUserDTO) {

    }

    @PostMapping("/idcheck")
    public String idchekP(String email, Model model){
        SecUserDTO secUserDTO = secUserService.findId(email);

        model.addAttribute("name", secUserDTO.getUsername());

        return "/user/idcheck";
    }




    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/profile","/modify"})
    public void profile(Principal principal, Model model) {

        log.info("회원정보");

        model.addAttribute("dto", secUserService.read(principal.getName())) ;

    }

    @PostMapping("/modify")
    public String usermodify(Principal principal, Model model) {
        log.info("회원수정");


        return null;
    }


    @PostMapping("/delete")
    public String delete(Principal principal, RedirectAttributes redirectAttributes, SecUserDTO secUserDTO) {



       secUserService.delete(secUserDTO.getSno());
       redirectAttributes.addFlashAttribute("result", "id가 삭제되었습니다.");

        return "redirect:/board/list";
    }



}
