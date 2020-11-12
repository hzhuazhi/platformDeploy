package com.xn.tvdeploy.dao;

import com.xn.common.dao.BaseDao;
import com.xn.tvdeploy.model.AccountTpModel;

/**
 * @author df
 * @Description:渠道账号的Dao层
 * @create 2018-09-11 14:42
 **/
public interface AccountTpDao <T> extends BaseDao<T> {

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
}
