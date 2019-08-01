package com.github.binarywang.demo.wx.mp.service;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Jovan
 * @create 2019/7/26
 */
@Service
@Slf4j
public class WxMessageService {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WxMpQrcodeService qrcodeService;

    @Autowired
    private WxMpMaterialService materialService;

    public String messageHandler(WxMpXmlMessage inMessage) {
        String out = null;
        String msgType = inMessage.getMsgType();
        log.info("[AN MESSAGE TYPE] - {} ", msgType);
        switch (msgType) {
            case "text":
                if (inMessage.getContent().startsWith("二维码-")){
                    try {
                        String name = inMessage.getContent().substring(4);
                        WxMpQrCodeTicket ticket = qrcodeService.qrCodeCreateTmpTicket(name, 2592000);
                        File qrPic = qrcodeService.qrCodePicture(ticket);
                        out = generateImgOutMessage(inMessage, qrPic);
                    } catch (WxErrorException e) {
                        e.printStackTrace();
                    }
                } else {
                    out = generateTextOutMessage(inMessage, "这是文本消息");
                }
                break;
            case "event":
                String event = inMessage.getEvent();
                log.info("[AN EVENT MESSAGE] - {}", event);
                if (event.equals("subscribe")) {
                    out = generateTextOutMessage(inMessage, "谢谢您的关注，好人一生平安");
                } else if (event.equals("SCAN")) {
                    log.info("{} - 你好", inMessage.getEventKey());
                    out = generateTextOutMessage(inMessage, inMessage.getEventKey() + "，你好！");
                }
                break;
            case "image":
                out = generateTextOutMessage(inMessage, "这是图片消息");
                break;
            case "voice":
                out = generateTextOutMessage(inMessage, "这是语音消息");
                break;
            case "video":
                out = generateTextOutMessage(inMessage, "这是视频消息");
                break;
            case "shortvideo":
                out = generateTextOutMessage(inMessage, "这是短视频消息");
                break;
            case "location":
                out = generateTextOutMessage(inMessage, "这是地理位置消息");
                break;
            case "link":
                out = generateTextOutMessage(inMessage, "这是链接消息");
                break;
            default:
                out = "success";
        }
        return out;
    }

    private String generateTextOutMessage(WxMpXmlMessage inMessage, String content) {
        WxMpXmlOutTextMessage textMessage = WxMpXmlOutTextMessage.TEXT()
                                                .toUser(inMessage.getFromUser())
                                                .fromUser(inMessage.getToUser())
                                                .content(content)
                                                .build();
        return textMessage.toXml();
    }

    private String generateImgOutMessage(WxMpXmlMessage inMessage, File img) {
        WxMediaUploadResult result = null;
        try {
            result = materialService.mediaUpload("image", img);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        WxMpXmlOutImageMessage outImageMessage = WxMpXmlOutImageMessage.IMAGE()
            .toUser(inMessage.getFromUser())
            .fromUser(inMessage.getToUser())
            .mediaId(result.getMediaId())
            .build();
        return outImageMessage.toXml();
    }
}
