package jpabook.jpashop.controller.member;

import jakarta.validation.Valid;
import jpabook.jpashop.model.Address;
import jpabook.jpashop.model.Member;
import jpabook.jpashop.model.Name;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        Address address = new Address(memberForm.getNation(), memberForm.getCity(), memberForm.getStreet_1(), memberForm.getStreet_2(), memberForm.getZipcode());
        Name name = new Name(memberForm.getFirst_name(), memberForm.getMiddle_name(), memberForm.getLast_name());
        Member member = new Member();
        member.setName(name);
        member.setBirth(memberForm.getBirth());
        member.setEmail(memberForm.getEmail());
        member.setAddress(address);
        member.setUsername(memberForm.getUsername());
        try{
            memberService.join(member);

        }
        catch(IllegalStateException e){
            log.debug("duplicated email");
            e.printStackTrace();
            return "redirect:/members/new";
        }
        finally {
            return "redirect:/";
        }


    }
    @GetMapping("/members")
    public String membersList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
