package com.xn.tvdeploy.dao;


import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.StatisticsOutChannelModel;

/**
 * @author yoko
 * @desc 统计代付渠道的每日记录的Dao层
 * @create 2021-12-30 17:33
 **/
public interface StatisticsOutChannelDao<T> extends BaseDao<T> {

    /**
     * @Description: 获取total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public StatisticsOutChannelModel getTotalData(StatisticsOutChannelModel model);
}
