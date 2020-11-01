package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.ChannelOutModel;

/**
 * @Description 查询打款订单的Dao层
 * @Date 2020/3/26 21:49
 * @Version 1.0
 */
public interface ChannelOutDao<T> extends BaseDao<T> {
    public ChannelOutModel getTotalData(ChannelOutModel channelOutModel);
}
