package com.example.bo2.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "board")
@ToString
@Table(name = "Reply" , indexes = {@Index(name = "idx_reply_board_bno", columnList = "board_bno")})
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;    //참조대상

    private String replyText;   // 내용

    private  String replyer;    //작성자

    public void changeText(String text){
        this.replyText = text;
    }




}
