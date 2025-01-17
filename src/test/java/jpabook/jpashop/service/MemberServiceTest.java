package jpabook.jpashop.service;

import jpabook.jpashop.model.Address;
import jpabook.jpashop.model.Member;
import jpabook.jpashop.model.Name;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;


    @Test

    public void memberJoin() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("John");
        member.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member.setBirth(LocalDate.of(1982, 10, 25));
        member.setEmail("example-member-1@example.com");
        member.setName(new Name("John", "Doh", ""));

        Long memberId = memberService.join(member);
        Member findMember = memberRepository.findMemberByEmail("example-member-1@example.com");
        Assertions.assertEquals(member.getId(), findMember.getId());
        Assertions.assertEquals(member, findMember);


        //when

        //then

    }

    @Test
    public void duplicatedMemberException() throws Exception{
        Member member = new Member();
        member.setUsername("John");
        member.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member.setBirth(LocalDate.of(1982, 10, 25));
        member.setEmail("example-member-1@example.com");
        member.setName(new Name("John", "Doh", ""));

        Member member2 = new Member();
        member2.setUsername("John");
        member2.setAddress(new Address("US", "Ohio", "Jackson", "4235"));
        member2.setBirth(LocalDate.of(1982, 10, 25));
        member2.setEmail("example-member-1@example.com");
        member2.setName(new Name("John", "Doh", ""));

        memberService.join(member);
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));


    }

}