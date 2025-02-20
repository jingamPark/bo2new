package com.example.bo2.dto;

import com.example.bo2.entity.Board;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
//@ToString(exclude ="board")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long rno;

    private Long bno;
//    private Board board;    //참조대상

    private String replyText;   // 내용

    private  String replyer;    //작성자

    private LocalDate regDate;

    private LocalDate modDate;
}
