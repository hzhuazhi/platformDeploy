package com.xn.tvdeploy.service;


import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.StatisticsOutChannelModel;

/**
 * @Description 统计代付渠道的每日记录的Service层
 * @Author yoko
 * @Date 2020/3/2 17:29
 * @Version 1.0
 */
public interface StatisticsOutChannelService<T> extends BaseService<T> {

    /**
     * @Description: 获取total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsOutChannelModel getTotalData(StatisticsOutChannelModel model);

}
