package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //Item은 처음 데이터를 저장할 때는 id가 없다.->jpa가 제공하는 persist사용
            em.persist(item);   //신규등록
        } else {    //id가 있으면 이미 db에 저장된 엔티티를 수정
            em.merge(item); //merge는 강제update와 비슷
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id); //단건조회는 find를 이용
    }

    public List<Item> fidnAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList(); //전체조회는 jpql을 작성해야한다.

    }
}
