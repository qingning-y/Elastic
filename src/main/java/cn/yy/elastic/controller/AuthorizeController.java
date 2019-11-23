package cn.yy.elastic.controller;

import cn.yy.elastic.dto.AccessTokenDTO;
import cn.yy.elastic.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 授权
 */

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setCode(code);
        dto.setClient_id("72dae3380539c8868749");
        dto.setClient_secret("dfd932589bf5db3a4b42012aecbcee7d71e5c1f6");
        dto.setRedirect_uri("http://localhost:8080/callback");
        dto.setState(state);
        gitHubProvider.getAccessToken(dto);
        return "index";
    }
}