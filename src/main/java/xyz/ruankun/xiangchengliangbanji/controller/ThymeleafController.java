package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class ThymeleafController {

    @RequestMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("name","ruan");
        return "index";
    }
    @RequestMapping("/test")
    public String testPage(Model model){
        model.addAttribute("name","ruan");
        return "test";
    }
}
