package com.example.bo2.repository;

import com.example.bo2.entity.Board;
import com.example.bo2.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("select i from Item as i where i.item_name like concat('%',:keyword,'%') or i.item_content like concat('%',:keyword,'%')")
    Page<Item> findKeyword(String keyword, Pageable pageable);


}
