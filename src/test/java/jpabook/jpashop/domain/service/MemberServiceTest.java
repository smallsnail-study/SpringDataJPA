package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)    //Junit 실행 시 spring과 엮어서 실행
@SpringBootTest //스프링부트를 띄운 상태로 테스트 실행(스프링컨테이너 안에서 테스트실행)
@Transactional  //기본적으로 rollback 수행
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired  // @Transactional로 rollback을 수행하지만 db에 query문을 확인하기 위해
    EntityManager em;

    @Test
//    @Rollback(value = false)    // db에 데이터 반영 확인가능
    public void 회원가입() throws Exception { //회원가입을 성공해야한다.
        //given(주어졌을때)
        Member member = new Member();
        member.setName("kim");
        
        //when(실행하면)
        Long savedId = memberService.join(member);
        
        //then(결과)
        em.flush(); // 영속성컨텍스트에 있는 변경이나 등록내용을 db에 반영
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception { //회원가입 할 때 같은 이름이 있으면 예외가 발생해야 한다.
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);    //예외가 발생해야 한다!!!

        //then
        fail("예외가 발생해야 한다.");
    }

}