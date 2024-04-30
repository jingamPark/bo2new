package com.example.bo2;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {


    class SampleDTO {

        private String p1, p2, p3;
        public String getP1() {
            return p1;
        }
        public String getP2() {
            return p2;
        }
        public String getP3() {
            return p3;
        }

    }


    @GetMapping("/ex/ex2")
    public void ex2(Model model){
        log.info("ex2");


        List<String> stringList  = IntStream.range(1, 10)
                .mapToObj(i -> "data" +1).collect(Collectors.toList());


        Map<String, String > map = new HashMap<>();
        map.put("A", "AAAA");
        map.put("B", "BBBB");

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 = "Value p1";
        sampleDTO.p2 = "Value p2";
        sampleDTO.p3 = "Value p3";


        model.addAttribute("map", map);
        model.addAttribute("dto", sampleDTO);
        model.addAttribute("list", stringList);


    }


}
