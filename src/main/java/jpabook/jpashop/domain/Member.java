package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @JsonIgnore // 응답값에서 제외
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다관계, 하나의 회원이 여러상품을 주문, Order테이블에 있는 member에 의해서 매핑된 거울.(읽기전용),값을 넣는다고 해서 값이 변경되지 않는다.
    private List<Order> orders = new ArrayList<>();

}
