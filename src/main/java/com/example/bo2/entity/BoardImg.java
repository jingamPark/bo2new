package com.example.bo2.entity;



import com.example.bo2.entity.BaseEntity;
import com.example.bo2.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "board_img")
@ToString(exclude = "board")
@Getter
@Setter
public class BoardImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String imName; //중목을 제거하기위해서 uuid를 사용하면
    //새로운 파일명이 됨 경로포함

    private String oriImgName; //이미지 기존 파일명을 알기위해서 작성

    private String repimgYn; //저장되는 이미지 중에 대표 이미지인지를 지정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_bno")
    private Board board; //board 테이블을 참조, 누구의 이미지인가

}
