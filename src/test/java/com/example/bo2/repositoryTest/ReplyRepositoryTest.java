package com.example.bo2.repositoryTest;

import com.example.bo2.entity.Board;
import com.example.bo2.entity.Reply;
import com.example.bo2.repository.BoardRepository;
import com.example.bo2.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;
    @Test
    public void register(){
        Board board = Board.builder()
                .bno(3L)
                .build();
        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글본문")
                .replyer("작성자")
                .build();

         log.info(replyRepository.save(reply));
    }


    @Test
    public void registerinserAll(){
        Board board = Board.builder()
                .bno(11L)
                .build();
        for(int i = 1; i<200; i++){

            Reply reply = Reply.builder()
                    .board(board)
                    .replyText("댓글본문" + i)
                    .replyer("작성자" + i)
                    .build();

            log.info(replyRepository.save(reply));
        }
    }

    @Test
    public void relpyListTest(){

        replyRepository.listReplyfrombno(10L)
                .forEach(reply -> log.info(reply));

    }

    @Test
    public void relpyListTest2(){

        Board board = Board.builder()
                .bno(10L)
                .build();

        replyRepository.findByBoard(board)
                .forEach(reply -> log.info(reply));

    }

    @Test
    @Transactional
    public void relpyListTest3(){


        replyRepository.findByBoard_Bno(10L)
                .forEach(reply -> log.info(reply));

    }

    @Test
    @Transactional
    public void lostOfBoardTest(){
                                            // 0번째는 1페이지 , 5개씩 , rno로 내림차순
        Pageable pageable = PageRequest.of(0, 5, Sort.by("rno").descending());

        replyRepository.listOfBoard(10L, pageable)
                .forEach(reply -> log.info(reply));

        Page<Reply> a = replyRepository.listOfBoard(10L, pageable);

//        a.getContent();
//        a.getSize();
//        a.getTotalElements();
//        a.getNumber();


    }

    @Test
    public void count(){
        log.info(replyRepository.countByBoard_Bno(11L));
    }


    @Test
    public void findaaatest(){

        replyRepository.findaaa().forEach(reply -> log.info(reply));

    }



















}
