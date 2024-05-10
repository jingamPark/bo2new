package com.example.bo2.serviceTest;

import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void registerTest() {

        ItemDTO itemDTO = ItemDTO.builder()
                .item_name("책장")
                .item_content("책을보관함")
                .item_price(3000L)
                .item_count(10L)
                .seller("신짱구")
                .build();


        itemService.register(itemDTO);


    }

    @Test
    public void read() {
        ItemDTO itemDTO = itemService.read(1L);


        log.info(itemDTO);


    }

    @Test
    public void listTest() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .page(1)
                .keyword("의자")
                .build();

        PageResponseDTO<ItemDTO> aa =itemService.list(pageRequestDTO);
        log.info(aa);

    }


    @Test
    public void modifyTest() {
        ItemDTO itemDTO = ItemDTO.builder()
                .ino(1L)
                .item_name("사물함")
                .item_content("튼튼함")
                .item_price(3002L)
                .item_count(11L)
                .build();

        itemService.modify(itemDTO);

        log.info(itemService.read(1L));
    }

    @Test
    public void removeTest() {
        itemService.remove(98L);
        log.info(itemService.read(98L));
    }
}
