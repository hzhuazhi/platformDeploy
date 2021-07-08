package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.ChannelgewayModel;

import java.util.List;

/**
 * @Description 渠道与通道的关联关系的Service层
 * @Author yoko
 * @Date 2020/4/1 16:06
 * @Version 1.0
 */
public interface ChannelgewayService <T> extends BaseService<T> {

    /**
     * @Description: 获取渠道与通道关联关系的数据
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/7/8 13:59
    */
    public List<ChannelgewayModel> getChannelgewayInfo(ChannelgewayModel model);
}
