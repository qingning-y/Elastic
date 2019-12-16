package cn.yy.elastic.controller;

import cn.yy.elastic.dto.PaginationDTO;
import cn.yy.elastic.dto.QuestionDTO;
import cn.yy.elastic.mapper.QuestionMapper;
import cn.yy.elastic.mapper.UserMapper;
import cn.yy.elastic.model.Question;
import cn.yy.elastic.model.User;
import cn.yy.elastic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String  search) {
        PaginationDTO pagination = questionService.list(page, size,search);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search",search);
        return "index";
    }
}