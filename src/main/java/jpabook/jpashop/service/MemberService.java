package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA 읽기 성능 최적화
@RequiredArgsConstructor    // final이 붙은 필드만 가지고 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

    /* @RequiredArgsConstructor이 만들어주는 생성자
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     */
    @Transactional  // 쓰기에서는 readOnly 적용안해야 수정가능
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원 검증(동일이름검증)
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException(("이미 존재하는 회원입니다."));
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
