package com.example.bo2.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity{

      //base에서 작성일 수정일


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;   //상품번호

    @Column(length = 500, nullable = false)
    private String item_name; //상품명
    @Column(length = 2000, nullable = false)
    private String item_content;    //상품 상세설명

    @Column(nullable = false)
    private Long item_count;    //상품수량

    @Column(nullable = false)
    private Long item_price;    //상품가격

    @Column(length = 50, nullable = false)
    private String seller;  //판매자

    public void chang(String item_name , String item_content, Long item_count, Long item_price){


        this.item_count = item_count;
        this.item_price = item_price;
        this.item_name = item_name;
        this.item_content = item_content;

    }



}
