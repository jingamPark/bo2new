package com.example.bo2.dto;


import com.example.bo2.entity.BoardImg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class BoardImgDTO {

    private Long ino;

    private String imName; //중목을 제거하기위해서 uuid를 사용하면
    //새로운 파일명이 됨 경로포함

    private String oriImgName; //이미지 기존 파일명을 알기위해서 작성

    private String repimgYn; //저장되는 이미지 중에 대표 이미지인지를 지정

    private LocalDate regDate;
    private LocalDate modDate;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ModelMapper getMapper(){
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
    public static BoardImgDTO of(BoardImg boardImg){
        return getMapper().map(boardImg, BoardImgDTO.class);
    }

}
