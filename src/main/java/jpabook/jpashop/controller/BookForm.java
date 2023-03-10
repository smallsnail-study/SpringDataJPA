package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    //상품관련 공통속성
    private Long id;    // 상품수정을 위해 id값이 필요

    private String name;
    private int price;
    private int stockQuantity;

    //책만의 개별 속성
    private String author;
    private String isbn;
}
