package com.example.bo2.controller;


import com.example.bo2.dto.BoardDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO){

        boardService.register(boardDTO);

        return "redirect:list";
    }
    @GetMapping("/list")
    public void list(Model model){
        List<Board> boardList = boardService.select();
        model.addAttribute("list", boardList);
        boardList.forEach(board -> log.info(board));

    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno){

    }
    @PostMapping("modify")
    public String modify(Board board){
        boardService.modify(board);
        return null;
    }
    @PostMapping("/remove")
    public void remove(Long bno){
        boardService.remove(bno);
    }



}
