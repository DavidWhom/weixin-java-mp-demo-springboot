package com.github.binarywang.demo.wx.mp.service;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpUserServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jovan
 * @create 2019/7/25
 */
@Service
public class WxMpUserService extends WxMpUserServiceImpl {
    public WxMpUserService(WxMpService wxMpService) {
        super(wxMpService);
    }
}
