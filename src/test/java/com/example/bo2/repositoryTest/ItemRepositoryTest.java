package com.example.bo2.repositoryTest;


import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.entity.Item;
import com.example.bo2.repository.ItemRepository;
import jakarta.persistence.Table;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ItemRepositoryTest {

    @Autowired
    public ItemRepository itemRepository;


    @Test
    public void saveTest() {


        for (int i =1 ; i<50 ; i++){
            Item item = Item.builder()
                    .item_name("의자")
                    .item_content("편하게 앉을 수 있 ")
                    .item_count(10L)
                    .item_price(3000L)
                    .seller("신형만")
                    .build();
            itemRepository.save(item);
        }




    }

    @Test
    @Transactional
    public void findKeywordTest() {
        String keyword = "의자";

        Pageable pageable = PageRequest.of(0, 7, Sort.by("ino").descending());


        Page<Item> itemPage= itemRepository.findKeyword(keyword,pageable);

        log.info(itemPage);


    }




}
