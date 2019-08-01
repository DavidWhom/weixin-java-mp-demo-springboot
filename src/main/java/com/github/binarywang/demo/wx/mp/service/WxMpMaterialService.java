package com.github.binarywang.demo.wx.mp.service;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Jovan
 * @create 2019/7/26
 */
@Service
public class WxMpMaterialService extends WxMpMaterialServiceImpl {
    public WxMpMaterialService(WxMpService wxMpService) {
        super(wxMpService);
    }
}
