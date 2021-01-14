package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.GewayModel;

/**
 * @Description 通道的Service层
 * @Author yoko
 * @Date 2020/3/31 20:49
 * @Version 1.0
 */
public interface GewayService <T> extends BaseService<T> {

    /**
     * @Description: 更新用户的密码
     * <p>
     *     更新登录密码
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/11/15 17:53
     */
    public int updatePassWd(GewayModel model);
}
