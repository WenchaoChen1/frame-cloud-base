

package com.gstdev.cloud.access.wxapp.processor;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaXmlOutMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p>Description: 微信小程序Log处理器 </p>
 *
 * @author : cc
 * @date : 2021/4/7 12:44
 */
public class WxappLogHandler implements WxMaMessageHandler {

    private static final Logger log = LoggerFactory.getLogger(WxappLogHandler.class);

    @Override
    public WxMaXmlOutMessage handle(WxMaMessage wxMaMessage, Map<String, Object> context, WxMaService wxMaService, WxSessionManager sessionManager) throws WxErrorException {
        log.info("收到消息：" + wxMaMessage.toString());
        wxMaService.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMaMessage.toJson())
                .toUser(wxMaMessage.getFromUser()).build());
        return null;
    }
}
