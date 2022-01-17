package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.ChannelChangeModel;

/**
 * @author df
 * @Description:渠道加金额的Service层
 * @create 2018-09-13 16:35
 **/
public interface ChannelChangeService<T> extends BaseService<T> {

    /**
     * @Description: 获取total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public ChannelChangeModel getTotalData(ChannelChangeModel model);
}
