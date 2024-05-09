package com.example.bo2.controller;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    private final BoardService boardService;

    @PostMapping("/aa")
    public Board register(BoardDTO boardDTO) {
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .build();

        boardService.register(boardDTO);

        return board;
    }


//    @GetMapping("/bb")
//    public List<Board> boardList() {
//        List<Board>
//    }


    @GetMapping("/a")
    public String a() {
        return "신짱아";
    }

    @GetMapping("/b")
    public Board b() {
        return Board.builder()
                .bno(11L)
                .title("제목")
                .build();
    }


    @GetMapping("/c")
    public List<Board> c() {

        List<Board> boardList = new ArrayList<>();

        for (long i = 1 ; i <100; i++) {
            Board board = Board.builder()
                    .bno(i)
                    .title("제목" + i)
                    .build();
            boardList.add(board);


        }
        return boardList;
    }


    @GetMapping("/d")
    public String d(Board board) {
        log.info(board);
        return "환영합니다";
    }

    @PostMapping("/e")
    public String e(@RequestBody Board board) {
        log.info(board.getTitle());
        return "포스트전송입니다.";
    }

    @PutMapping("/f")
    public String f(@RequestBody Board board) {
        log.info(board.getTitle());
        return "포스트전송입니다.";
    }


    @GetMapping("/h")
    public ResponseEntity<Board> h() {
        Board board = Board.builder()
                .bno(11L)
                .title("제목")
                .build();
        if (true) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
        }




    }

}//class
