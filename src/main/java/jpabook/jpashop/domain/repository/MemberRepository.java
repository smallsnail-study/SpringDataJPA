package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;   //spring이 매니저를 생성해서 주입

    public void save(Member member) {   // 저장
        em.persist(member);
    }

    public Member findOne(Long id) {    // 단건조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {     // 전체조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {   // 이름으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
