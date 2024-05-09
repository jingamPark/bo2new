package com.example.bo2.service;

import com.example.bo2.dto.*;
import com.example.bo2.entity.Board;
import com.example.bo2.entity.Item;

import java.util.List;

public interface ItemService {

    //등록
    public Long register(ItemDTO itemDTO);
    //목록
    public List<Item> select();

    //페이징처리 , 검색처리, 목록
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO);

//    //페이징처리, 검색처리, 댓글수량, 목록
//   public PageResponseDTO<BoardListReplyCountDTO>listWithReplyCount(PageRequestDTO pageRequestDTO);
//
    //상세보기
    public ItemDTO read(Long ino);
    //수정
    public void modify(ItemDTO itemDTO);
    //삭제
    public void remove(Long ino);






}
