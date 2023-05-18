package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class FirstController {
    @GetMapping("/hi")  //url 주소
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "Yun");
        return "greetings";  //greetings.mustache 를 브라우저로 전송
    }
    @GetMapping("/bye")
    public String seeyounext(Model model){
        model.addAttribute("nickname", "YunGwangJin");
        return "goodbye";
    }
    @GetMapping("/design")
    public String design(){
        return "design";
    }
}
