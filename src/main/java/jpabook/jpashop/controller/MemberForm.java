package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    // 필수값 설정 : 값이 비어있으면 오류발생
    // (build.gradle에 implementation 'org.springframework.boot:spring-boot-starter-validation' 추가)
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
