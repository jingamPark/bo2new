package com.example.bo2.service;

import com.example.bo2.dto.*;
import com.example.bo2.entity.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyService {


    public Long register(ReplyDTO replyDTO);

    PageResponseDTO<ReplyDTO> getListOfBoard
            (Long bno, PageRequestDTO pageRequestDTO);



    public ReplyDTO read(Long rno);

    public void modify(ReplyDTO replyDTO);

    public void remove(Long rno);
}
