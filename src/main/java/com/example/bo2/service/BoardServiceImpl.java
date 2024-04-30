package com.example.bo2.service;

import com.example.bo2.entity.Board;
import com.example.bo2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void register(Board board) {
        boardRepository.save(board);
    }

    @Override
    public List<Board> select() {
        return boardRepository.findAll();
    }
    @Override
    public Board read(Long bno) {
        Optional<Board> board
                = boardRepository.findById(bno);
        return board.get();
    }
    @Override
    public void modify(Board board) {
        boardRepository.save(board);
    }
    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
}
