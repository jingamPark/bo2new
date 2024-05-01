package com.example.bo2.service;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Log4j2
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);


        Long bno =  boardRepository.save(board).getBno();

        return bno;
    }

    @Override
    public List<Board> select() {
        return boardRepository.findAll();
    }
    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> board
                = boardRepository.findById(bno);

        //log.info("값확인 " + board.isEmpty());

        if(board.isEmpty()){
            return null;
        }

        BoardDTO boardDTO = modelMapper.map(board.get(), BoardDTO.class);

        return boardDTO;
    }



    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.chang(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);

//        Board board = modelMapper.map(boardDTO, Board.class);
//
//        boardRepository.save(board);
    }
    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
}
