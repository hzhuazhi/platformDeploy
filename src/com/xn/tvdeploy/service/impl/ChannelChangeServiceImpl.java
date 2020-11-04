package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.BankDao;
import com.xn.tvdeploy.dao.ChannelChangeDao;
import com.xn.tvdeploy.service.BankService;
import com.xn.tvdeploy.service.ChannelChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道加金额的实现层
 * @Author yoko
 * @Date 2020/3/26 19:37
 * @Version 1.0
 */
@Service("channelChangeService")
public class ChannelChangeServiceImpl<T> extends BaseServiceImpl<T> implements ChannelChangeService<T> {

    private static Logger log=Logger.getLogger(ChannelChangeServiceImpl.class);

    @Autowired
    private ChannelChangeDao<T> channelChangeDao;
    @Override
    public ChannelChangeDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelChangeDao;
    }
}
