package com.example.bo2.service;

import com.example.bo2.dto.BoardDTO;
import com.example.bo2.dto.ItemDTO;
import com.example.bo2.dto.PageRequestDTO;
import com.example.bo2.dto.PageResponseDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.entity.Item;
import com.example.bo2.entity.Reply;
import com.example.bo2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
@Transactional
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;


    @Override
    public Long register(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);

        Long ino = itemRepository.save(item).getIno();

        return ino;
    }

    @Override
    public List<Item> select() {
        return itemRepository.findAll();
    }


    /*아마 에러날듯*/
    @Override
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO) {


        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = pageRequestDTO.getPageable("ino");

        Page<Item> itemPage = itemRepository.findKeyword(keyword, pageable);

        List<ItemDTO> itemDTOList =
                itemPage.getContent().stream()
                        .map(item -> modelMapper.map(item, ItemDTO.class))
                        .collect(Collectors.toList());



        return  PageResponseDTO.<ItemDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(itemDTOList)
                .total((int)itemPage.getTotalElements())
                .build();

    }

    @Override
    public ItemDTO read(Long ino) {
        Optional<Item> item
                = itemRepository.findById(ino);

        log.info("값확인 " + item.isEmpty());

        if(item.isEmpty()){
            return null;
        }

        ItemDTO itemDTO = modelMapper.map(item.get(), ItemDTO.class);

        return itemDTO;

    }

    @Override
    public void modify(ItemDTO itemDTO) {

        Optional<Item> result = itemRepository.findById(itemDTO.getIno());

        Item item =result.orElseThrow();

        item.chang(itemDTO.getItem_name(), itemDTO.getItem_content(), itemDTO.getItem_count(), itemDTO.getItem_price());


        itemRepository.save(item);

    }

    @Override
    public void remove(Long ino) {
        itemRepository.deleteById(ino);
    }
}
