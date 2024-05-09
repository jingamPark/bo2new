package com.example.bo2.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {

    private Long ino;   //상품번호

    @NotEmpty
    @Size(min=3, max = 100)
    private String item_name; //상품명

    @NotEmpty
    private String item_content;    //상품 상세설명

    private Long item_count;    //상품수량

    private Long item_price;    //상품가격

    @NotEmpty
    private String seller;  //판매자


    private LocalDate regDate;
    private LocalDate modDate;



}
