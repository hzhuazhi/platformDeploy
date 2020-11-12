package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.ChannelBalanceDeductDao;
import com.xn.tvdeploy.service.ChannelBalanceDeductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道扣减余额流水的Service层的实现层
 * @Author yoko
 * @Date 2020/10/31 16:26
 * @Version 1.0
 */
@Service("channelBalanceDeductService")
public class ChannelBalanceDeductServiceImpl<T> extends BaseServiceImpl<T> implements ChannelBalanceDeductService<T> {
    /**
     * 5分钟.
     */
    public long FIVE_MIN = 300;

    public long TWO_HOUR = 2;

    @Autowired
    private ChannelBalanceDeductDao<T> channelBalanceDeductDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelBalanceDeductDao;
    }
}
