package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.ChannelOutModel;

/**
 * @Description 提现的Service层
 * @Author yoko
 * @Date 2020/3/26 21:53
 * @Version 1.0
 */
public interface ChannelOutService<T> extends BaseService<T> {
    public ChannelOutModel getTotalData(ChannelOutModel channelOutModel);
}
