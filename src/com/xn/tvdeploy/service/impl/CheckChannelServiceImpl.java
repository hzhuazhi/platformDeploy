package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.CheckChannelDao;
import com.xn.tvdeploy.service.CheckChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:
 * @Description: 审核人关联的渠道：审核人员负责审核的渠道，进行关联关系的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("checkChannelService")
public class CheckChannelServiceImpl <T> extends BaseServiceImpl<T> implements CheckChannelService<T> {
    private final static Logger log = Logger.getLogger(CheckChannelServiceImpl.class);
    @Autowired
    private CheckChannelDao checkChannelDao;

    @Override
    public BaseDao<T> getDao() {
        return checkChannelDao;
    }
}
