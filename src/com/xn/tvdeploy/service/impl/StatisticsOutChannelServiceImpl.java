package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.StatisticsOutChannelDao;
import com.xn.tvdeploy.model.StatisticsOutChannelModel;
import com.xn.tvdeploy.service.StatisticsOutChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 统计代收渠道的每日记录的Service层的实现层
 * @Author yoko
 * @Date 2020/3/2 17:30
 * @Version 1.0
 */
@Service("statisticsOutChannelService")
public class StatisticsOutChannelServiceImpl<T> extends BaseServiceImpl<T> implements StatisticsOutChannelService<T> {
    private static Logger log= Logger.getLogger(StatisticsOutChannelServiceImpl.class);

    @Autowired
    private StatisticsOutChannelDao statisticsOutChannelDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return statisticsOutChannelDao;
    }

    @Override
    public StatisticsOutChannelModel getTotalData(StatisticsOutChannelModel model) {
        return statisticsOutChannelDao.getTotalData(model);
    }

}
