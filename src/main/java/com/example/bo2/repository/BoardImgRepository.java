package com.example.bo2.repository;


import com.example.bo2.entity.BoardImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImg, Long> {

    public List<BoardImg> findByBoard_BnoOrderByIno(Long bno); //본문에 달린 이미지 파일 찾기


    @Query(value = "select i from BoardImg i where i.board.bno = :bno")
    public List<BoardImg> findBybno (Long bno);
    //    @Query(value = "select * from BoardImg i where i.board.bno = :bno", nativeQuery = true)
    //    nativeQuery = true 일반적인 sql문처럼 작성가능


}
