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

    @Test
    public void readTest(){

       BoardDTO boardDTO= boardService.read(250L);

       log.info(boardDTO);

//       if (boardDTO == null ){
//           redirectAttributes.adda
//           return "redirect:/board.list";
//       }

       log.info(boardService.read(250L));

    }

    @Test
    public void modifytest(){

        boardService.modify(BoardDTO.builder()
                .bno(250L).title("서비스에서 ")
                .content("수정한내용")
                .build());

        log.info(boardService.read(250L));

    }

    @Test
    public void deltest(){


        boardService.remove(250L);
    }




}
