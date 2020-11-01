package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.ChannelOutDao;
import com.xn.tvdeploy.dao.WithdrawDao;
import com.xn.tvdeploy.model.ChannelOutModel;
import com.xn.tvdeploy.service.ChannelOutService;
import com.xn.tvdeploy.service.WithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 提现的Service层的实现层
 * @Author yoko
 * @Date 2020/3/27 9:55
 * @Version 1.0
 */
@Service("channelOutService")
public class ChannelOutServiceImpl<T> extends BaseServiceImpl<T> implements ChannelOutService<T> {

    private static Logger log=Logger.getLogger(ChannelOutServiceImpl.class);

    @Autowired
    private ChannelOutDao<T> channelOutDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelOutDao;
    }

    @Override
    public ChannelOutModel getTotalData(ChannelOutModel  channelOutModel) {
        return channelOutDao.getTotalData(channelOutModel);
    }
}
