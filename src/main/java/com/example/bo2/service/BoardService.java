package com.example.bo2.service;

import com.example.bo2.entity.Board;

import java.util.List;

public interface BoardService {

    public void register(Board board);

    public List<Board> select();

    public Board read(Long bno);

    public void modify(Board board);

    public void remove(Long bno);

}
