package com.example.bo2.controller;


import com.example.bo2.dto.BoardDTO;
import com.example.bo2.dto.BoardListReplyCountDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;


import com.example.bo2.service.BoardImgService;
import com.example.bo2.service.BoardService;
import com.example.bo2.service.ReplyService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.List;


@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final ReplyService replyService;

    private final BoardImgService boardImgService;




    @GetMapping("/register")
    public void register(){


    }
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList
    ){

        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes
                    .addFlashAttribute("errors", bindingResult.getAllErrors());
            log.info(bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Long bno =  boardService.register(boardDTO);

        boardImgService.register(bno, boardImgFileList);

        redirectAttributes.addFlashAttribute("result", bno + "글이 등록되었습니다.");

        return "redirect:/board/list";
    }
    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO  ){
//        List<Board> boardList = boardService.select();
//        PageResponseDTO<BoardDTO> pageResponseDTO =  boardService.list(pageRequestDTO);
        PageResponseDTO<BoardListReplyCountDTO> pageResponseDTO
                = boardService.listWithReplyCount(pageRequestDTO);

//        model.addAttribute("list", pageResponseDTO.getDtoList());
        model.addAttribute("pageResponseDTO", pageResponseDTO);
//        boardList.forEach(board -> log.info(board));

    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, Model model, PageRequestDTO pageRequestDTO){

        model.addAttribute("dto", boardService.read(bno));
//        model.addAttribute("rDto", replyService.read(140L));
        model.addAttribute("imgdto", boardImgService.imglist(bno));

    }
    @PostMapping("/modify")
    public String modify(@Valid BoardDTO boardDTO ,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO,
                         Long[] ino,
                          @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList

    ){
        //boardService.modify(board);
        log.info("진입 포스트");
        // page 번호 넘기기
        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes
                    .addFlashAttribute("errors", bindingResult.getAllErrors());
            log.info(bindingResult.getAllErrors());
            redirectAttributes
                    .addAttribute("bno", boardDTO.getBno());

            return "redirect:/board/modify?"+pageRequestDTO.getLink();
        }



        boardService.modify(boardDTO);
        boardImgService.modify(ino);
        boardImgService.register(boardDTO.getBno(), boardImgFileList);

        redirectAttributes.addFlashAttribute("result", boardDTO.getBno() + "글이 수정되었습니다.");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/read?"+pageRequestDTO.getLink();
    }
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){

        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result",  bno + "글이 삭제되었습니다.");

        return "redirect:/board/list";
    }

//    @GetMapping("/sample")
//    public void sample() {
//
//    }
//
//    @ResponseBody
//    @GetMapping("/sam")
//    public Board sam() {
//        Board board = Board.builder()
//                .title("내용")
//                .build();
//        return board;
//    }

    @GetMapping("/board")
    public void board() {

    }
}
