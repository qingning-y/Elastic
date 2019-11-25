package cn.yy.elastic.controller;

import cn.yy.elastic.dto.AccessTokenDTO;
import cn.yy.elastic.dto.GitHubUser;
import cn.yy.elastic.mapper.UserMapper;
import cn.yy.elastic.model.User;
import cn.yy.elastic.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 授权
 */

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id(clientId);
        dto.setClient_secret(clientSecret);
        dto.setCode(code);
        dto.setRedirect_uri(redirectUrl);
        dto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(dto);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //token放入Cookie里，校验是否有Cookie
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}