package jpabook.jpashop.controller.member;

import jakarta.validation.Valid;
import jpabook.jpashop.model.Address;
import jpabook.jpashop.model.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        if(result.hasErrors()){
            result.getAllErrors().forEach(e -> log.debug(e.toString()));
            return "members/createMemberForm";
        }
        Address address = new Address(memberForm.getAddress().getNation(), memberForm.getAddress().getCity(), memberForm.getAddress().getStreet_1(),memberForm.getAddress().getStreet_2(),memberForm.getAddress().getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setBirth(memberForm.getBirth());
        member.setEmail(memberForm.getEmail());
        member.setUsername(memberForm.getUsername());
        memberService.join(member);
        return "redirect:/";

    }

}
