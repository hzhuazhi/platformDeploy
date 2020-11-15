package com.xn.tvdeploy.service;

import com.xn.common.service.BaseService;
import com.xn.tvdeploy.model.AccountTpModel;

/**
 * @author df
 * @Description:渠道账号的Service层
 * @create 2018-09-11 14:40
 **/
public interface AccountTpService <T> extends BaseService<T> {

    /**
     * @Description: 更新渠道的余额
     * <p>
     *     余额加减，
     *     字段addBalance不为空，则余额加；
     *     字段subtractBalance不为空，则余额减
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/10/30 17:04
     */
    public int updateBalance(AccountTpModel model);

    /**
     * @Description: 更新用户的密码
     * <p>
     *     更新登录密码，提现密码
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/11/15 17:53
     */
    public int updatePassWd(AccountTpModel model);
}
