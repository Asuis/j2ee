package com.asuis.j2ee.service;

import java.util.HashMap;
import java.util.List;

/**
 * 通知服务
 * 获取个人通知
 * 删除通知
 * 记录已读
 * 发送通知
 * @author 15988440973
 */
public interface MessageService {
    /**获取个人通知*/
    public List getNoticePersonal();
    /**根据信息删除通知或者设置已读*/
    public HashMap<String,String> updateNotice();
    /**发送通知*/
    public HashMap<String,String> sendMessage();
}
