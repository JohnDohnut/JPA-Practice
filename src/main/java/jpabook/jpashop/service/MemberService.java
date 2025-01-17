package jpabook.jpashop.service;


import jpabook.jpashop.model.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicatedMember(member);
        return memberRepository.save(member);

    }

    @Transactional(readOnly = true)
    public void validateDuplicatedMember(Member member){
        Member findMember = memberRepository.findMemberByEmail(member.getEmail());
        if(findMember!=null){
            throw new IllegalStateException("Duplicated Member");
        }
    }
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }



}
