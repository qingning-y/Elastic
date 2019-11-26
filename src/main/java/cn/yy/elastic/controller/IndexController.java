package cn.yy.elastic.controller;

import cn.yy.elastic.mapper.UserMapper;
import cn.yy.elastic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        /**
         * request:可以获取Cookies
         * response:返回浏览器，设置Cookies
         */
        /**
         * 访问首页的时候，循环看所有的Cookies,找到Cookies等于token,拿到Cookies，就去数据库里面查询是否有这个Cookies记录
         * 如果有就把user返回到session里面
         */
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}