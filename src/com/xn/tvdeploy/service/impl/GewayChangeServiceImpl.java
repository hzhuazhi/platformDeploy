package com.xn.tvdeploy.service.impl;

import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.GewayChangeDao;
import com.xn.tvdeploy.service.GewayChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 通道金额变更纪录的Service层的实现层
 * @Author yoko
 * @Date 2021/1/15 15:05
 * @Version 1.0
 */
@Service("gewayChangeService")
public class GewayChangeServiceImpl<T> extends BaseServiceImpl<T> implements GewayChangeService<T> {

    private static Logger log=Logger.getLogger(GewayChangeServiceImpl.class);

    @Autowired
    private GewayChangeDao<T> gewayChangeDao;
    @Override
    public GewayChangeDao<T> getDao() {
        // TODO Auto-generated method stub
        return gewayChangeDao;
    }
}
