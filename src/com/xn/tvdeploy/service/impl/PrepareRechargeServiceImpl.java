package com.xn.tvdeploy.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.tvdeploy.dao.PrepareRechargeDao;
import com.xn.tvdeploy.dao.StrategyDao;
import com.xn.tvdeploy.service.PrepareRechargeService;
import com.xn.tvdeploy.service.StrategyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 策略的Service层的实现层
 * @Author yoko
 * @Date 2020/9/18 19:10
 * @Version 1.0
 */
@Service("prepareRechargeService")
public class PrepareRechargeServiceImpl<T> extends BaseServiceImpl<T> implements PrepareRechargeService<T> {
    private static Logger log= Logger.getLogger(PrepareRechargeServiceImpl.class);

    @Autowired
    private PrepareRechargeDao prepareRechargeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return prepareRechargeDao;
    }
}
