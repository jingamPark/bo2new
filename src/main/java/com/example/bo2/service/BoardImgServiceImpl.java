package com.example.bo2.service;


import com.example.bo2.dto.BoardImgDTO;
import com.example.bo2.entity.Board;
import com.example.bo2.entity.BoardImg;
import com.example.bo2.repository.BoardImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardImgServiceImpl implements BoardImgService {

    private final BoardImgRepository boardImgRepository;
    private final ModelMapper modelMapper;

//    @Value("c:/upload/")
    @Value("${project.uploadpath}")
    private String uploadpath;

    //날짜별로 폴더를 생성하는 메소드
    public String makeDir(){
        Date date = new Date();
        SimpleDateFormat yymmdd = new SimpleDateFormat("yyMMdd");
        String now = yymmdd.format(date);
        //240513

        String path = uploadpath + "\\" + now; //풀경로
        //c:/upload\240513

        File file = new File(path);

        if (file.exists() == false){ //exists 파일이 존재한다면 true, 존재x false
            file.mkdir(); //폴더생성
        }

        return path;
    }

    @Override
    public void register(Long bno, List<MultipartFile> multipartFileList) {
        log.info("boardimgserviceBno : " + bno);

        if (multipartFileList != null && multipartFileList.size() > 0){
            for( MultipartFile a : multipartFileList ){
                if (a.getOriginalFilename().length() == 0){
                    continue;
                }
                String origin = a.getOriginalFilename();
                log.info(origin);
                // /aaa/aaa/aa.png 중 마지막 /
                String originName = origin.substring(origin.lastIndexOf("\\") + 1);
                //파일명이 생김 file 객체의 경로는 경로 + 파일명
                String filepath = makeDir(); //파일을 저장할 경로
                // c:/upload/240513/개발자.png
                String uuid = UUID.randomUUID().toString();
                log.info("유유아이디 : " + uuid);
                //db 저장용
                String newName = uuid + "_" + originName;

                //저장시 사용
                String saveName = filepath + "\\" + uuid + "_" + originName;
                log.info(saveName);
                File save = new File(saveName);

                try {
                    a.transferTo(save);
                }catch (IOException e){

                }
                //저장한 파일의 제반사항을 db에 저장
                Board board = Board.builder()
                        .bno(bno)
                        .build();

                BoardImg boardImg = new BoardImg();
                boardImg.setOriImgName(originName);
                boardImg.setImName(newName);
                boardImg.setBoard(board);
                boardImgRepository.save(boardImg);

            }
        }

    }

    @Override
    public List<BoardImgDTO> imglist(Long bno) {
        List<BoardImg> boardImgList = boardImgRepository.findByBoard_BnoOrderByIno(bno);

        List<BoardImgDTO> boardImgDTOList
                = new ArrayList<>();

        for ( BoardImg a : boardImgList ){
            BoardImgDTO boardImgDTO = new BoardImgDTO();
            boardImgDTO =  boardImgDTO.of(a);
            boardImgDTOList.add(boardImgDTO);
            log.info("날짜 : " + boardImgDTO.getRegDate());
        }

        return boardImgDTOList;
    }


    @Override
    public void modify(Long[] ino) {

        log.info(Arrays.toString(ino));


            for (int i = 0; i < ino.length; i++){
                if (ino[i] != null && ino[i] > 0){
                    Optional<BoardImg> boardImg = boardImgRepository.findById(ino[i]);


                    BoardImgDTO boardImgDTO = BoardImgDTO.of(boardImg.get());

                    log.info(boardImgDTO);

                    LocalDate date = boardImgDTO.getRegDate();
                    String Sdate = date.format(DateTimeFormatter.ofPattern("yyMMdd"));
                    String path = uploadpath + Sdate + boardImgDTO.getImName();

                    log.info(path);

                    File file = new File(path);
                    boardImgRepository.deleteById(ino[i]);

                }

            }

        }






        /*if (multipartFileList != null && multipartFileList.size() > 0){
            for( MultipartFile a : multipartFileList ){
                if (a.getOriginalFilename().length() == 0){
                    continue;
                }
                String origin = a.getOriginalFilename();
                log.info(origin);
                // /aaa/aaa/aa.png 중 마지막 /
                String originName = origin.substring(origin.lastIndexOf("\\") + 1);
                //파일명이 생김 file 객체의 경로는 경로 + 파일명
                String filepath = makeDir(); //파일을 저장할 경로
                // c:/upload/240513/개발자.png
                String uuid = UUID.randomUUID().toString();
                log.info("유유아이디 : " + uuid);
                //db 저장용
                String newName = uuid + "_" + originName;

                //저장시 사용
                String saveName = filepath + "\\" + uuid + "_" + originName;
                log.info(saveName);
                File save = new File(saveName);

                try {
                    a.transferTo(save);
                }catch (IOException e){

                }
                //저장한 파일의 제반사항을 db에 저장
                Board board = Board.builder()
                        .bno(bno)
                        .build();

                BoardImg boardImg = new BoardImg();
                boardImg.setOriImgName(originName);
                boardImg.setImName(newName);
                boardImg.setBoard(board);
                boardImgRepository.save(boardImg);

            }
        }*/

        //반복
/*        boardImgRepository.findById(ino[i]);
        File a = new File(경로);
        a.delete(); //물리적인 파일 삭제
        boardImgRepository.deleteById(번호);*/


    //File dele = new File(경로)
    //   dele.delete();

}
