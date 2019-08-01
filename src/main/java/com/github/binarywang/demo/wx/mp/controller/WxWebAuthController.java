package com.github.binarywang.demo.wx.mp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jovan
 * @create 2019/7/26
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/wx/auth")
public class WxWebAuthController {
    private final WxMpService wxService;
    @RequestMapping
    public void getUserInfo(@RequestParam(value = "code") String code,
                            @RequestParam(value = "state") String state){
        log.info("[AFTER AUTH] : code - {} , state - {}", code, state);
        try {
            WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(token, null);
            log.info("[GET USER BY AUTH] - {}", user.toString());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
