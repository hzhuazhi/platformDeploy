package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.ChannelgewayModel;

import java.util.List;

/**
 * @Description 渠道与通道的关联关系的Dao层
 * @Author yoko
 * @Date 2020/4/1 16:01
 * @Version 1.0
 */
public interface ChannelgewayDao <T> extends BaseDao<T> {

    /**
     * @Description: 获取渠道与通道关联关系的数据
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/7/8 13:59
     */
    public List<ChannelgewayModel> getChannelgewayInfo(ChannelgewayModel model);
}
