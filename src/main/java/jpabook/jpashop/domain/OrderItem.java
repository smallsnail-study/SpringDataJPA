package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문당시의 가격
    private int count;  //주문수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);    // orderItem을 생성할 때는 기본적으로 재고수량에서 주문수량을 빼준다.
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);  //아이템을 가져와서 재고수량을 주문수량만큼 늘려줘야한다.
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {    //주문할때 주문가격과 수량을 곱해서 계산해야한다.
        return getOrderPrice() * getCount();
    }
}
