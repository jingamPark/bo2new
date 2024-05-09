package com.example.bo2.serviceTest;


import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.dto.ReplyDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {

    @Autowired
    ReplyService  replyService;

    @Test
    public void registerTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("댓글")
                .replyer("작성자")
                .bno(1124L)
                .build();


        replyService.register(replyDTO);

    }



    @Test
    public void readTest(){

        log.info(replyService.read(605L));


    }
    @Test
    public void modifyTest(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(605L).replyText("수정한 댓글")
                .build();
        replyService.modify(replyDTO);

        replyService.remove(605L);

//        log.info(replyService.read(605L));
    }

    @Test
    @Transactional
    public void getListOfBoardTest(){

        //bno, pagerequest

        PageRequestDTO pageRequestDTO =  new PageRequestDTO();
        PageResponseDTO<ReplyDTO> getListOfBoard =
        replyService.getListOfBoard(10L, pageRequestDTO);

        getListOfBoard.getDtoList().forEach(
                replyDTO -> log.info(replyDTO)
        );



    }



}
