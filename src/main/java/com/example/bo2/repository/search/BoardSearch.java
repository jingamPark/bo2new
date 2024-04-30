package com.example.bo2.repository.search;


import com.example.bo2.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);


    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);


}
