package com.example.bo2.controller;


import com.example.bo2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController{
    private final ItemService itemService;


    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPost() {

        return null;
    }


    @GetMapping("/list")
    public void list() {

    }


    @GetMapping({"/modify", "/read"})
    public void modify() {

    }

    @PostMapping("/modify")
    public String modifyPost() {
        return null;
    }


    @PostMapping("/remove")
    public String remove() {
        return null;
    }

}//ItemController
