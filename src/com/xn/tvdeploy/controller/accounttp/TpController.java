package com.xn.tvdeploy.controller.accounttp;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.service.AccountTpService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 渠道基本信息
 * @Author yoko
 * @Date 2020/3/26 19:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/tp")
public class TpController extends BaseController {

    private static Logger log = Logger.getLogger(TpController.class);

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/tp/tpIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AccountTpModel model) throws Exception {
        List<AccountTpModel> dataList = new ArrayList<AccountTpModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setId(account.getId());
                log.info("");
                //不是管理员，只能查询自己的数据
            }
            dataList = accountTpService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取修改页面-跳转到修改密码
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate() {
        return "manager/tp/tpEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,AccountTpModel model) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                sendFailureMessage(response, "管理员无法操作,请使用渠道进行操作!");
                return;
            }
            // 根据账号ID查询渠道信息
            AccountTpModel accountTpQuery = new AccountTpModel();
            accountTpQuery.setId(account.getId());
            AccountTpModel accountTpModel = accountTpService.queryByCondition(accountTpQuery);
            if (accountTpModel == null || accountTpModel.getId() <= 0){
                sendFailureMessage(response, "错误,请重试!");
                return;
            }
            String passWd = "";
            String resetPassWd = "";
            String withdrawPassWd = "";
            String resetWithdrawPassWd = "";
            if (!StringUtils.isBlank(model.getPassWd())){
                passWd = model.getPassWd();
            }
            if (!StringUtils.isBlank(model.getResetPassWd())){
                resetPassWd = model.getResetPassWd();
            }
            if (!StringUtils.isBlank(model.getWithdrawPassWd())){
                withdrawPassWd = model.getWithdrawPassWd();
            }
            if (!StringUtils.isBlank(model.getResetWithdrawPassWd())){
                resetWithdrawPassWd = model.getResetWithdrawPassWd();
            }

            if (StringUtils.isBlank(passWd) && StringUtils.isBlank(withdrawPassWd)){
                sendFailureMessage(response, "请您填写要更改的原始登录密码或原始提现密码!");
                return;
            }

            AccountTpModel accountTpUpdate = new AccountTpModel();
            accountTpUpdate.setId(account.getId());
            if (!StringUtils.isBlank(passWd)){
                String md5PassWd = MD5.parseMD5(passWd);
                if (!accountTpModel.getPassWd().equals(md5PassWd)){
                    sendFailureMessage(response, "您填写的原始登录密码有误,请重新输入!");
                    return;
                }else{
                    if (StringUtils.isBlank(resetPassWd)){
                        sendFailureMessage(response, "请您填写新登录密码!");
                        return;
                    }else {
                        accountTpUpdate.setResetPassWd(MD5.parseMD5(resetPassWd));
                    }
                }
            }

            if (!StringUtils.isBlank(withdrawPassWd)){
                if (!accountTpModel.getWithdrawPassWd().equals(withdrawPassWd)){
                    sendFailureMessage(response, "您填写的原始提现密码有误,请重新输入!");
                    return;
                }else{
                    if (StringUtils.isBlank(resetWithdrawPassWd)){
                        sendFailureMessage(response, "请您填写新提现密码!");
                        return;
                    }else {
                        accountTpUpdate.setResetWithdrawPassWd(resetWithdrawPassWd);
                    }
                }
            }

            if (!StringUtils.isBlank(resetPassWd) || !StringUtils.isBlank(resetWithdrawPassWd)){
                accountTpService.updatePassWd(accountTpUpdate);
            }

            sendSuccessMessage(response, "保存成功~");
            return;
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }

}
