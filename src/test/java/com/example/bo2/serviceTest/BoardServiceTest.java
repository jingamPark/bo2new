package com.example.bo2.serviceTest;


import com.example.bo2.dto.BoardDTO;
import com.example.bo2.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    BoardService boardService;



    @Test
    public void registertest(){
        BoardDTO boardDTO  = BoardDTO.builder()
                .title("제목-1")
                .content("내용-1")
                .writer("작성자-1")
                .build();

        Long bno =  boardService.register(boardDTO);

        log.info(bno);
    }

}
