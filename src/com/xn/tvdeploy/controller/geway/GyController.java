package com.xn.tvdeploy.controller.geway;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.accounttp.TpController;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.GewayService;
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
 * @Description 通道的Controller层
 * @Author yoko
 * @Date 2021/1/14 16:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/gy")
public class GyController extends BaseController {

    private static Logger log = Logger.getLogger(GyController.class);

    @Autowired
    private GewayService<GewayModel> gewayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/gy/gyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, GewayModel model) throws Exception {
        List<GewayModel> dataList = new ArrayList<GewayModel>();
        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = gewayService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 获取修改页面-跳转到修改密码
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate() {
        return "manager/gy/gyEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,GewayModel model) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_GEWAY){
                sendFailureMessage(response, "管理员无法操作,请使用渠道进行操作!");
                return;
            }
            // 根据账号ID查询渠道信息
            GewayModel accountTpQuery = new GewayModel();
            accountTpQuery.setId(account.getId());
            GewayModel GewayModel = gewayService.queryByCondition(accountTpQuery);
            if (GewayModel == null || GewayModel.getId() <= 0){
                sendFailureMessage(response, "错误,请重试!");
                return;
            }
            String passWd = "";
            String resetPassWd = "";
            if (!StringUtils.isBlank(model.getPassWd())){
                passWd = model.getPassWd();
            }
            if (!StringUtils.isBlank(model.getResetPassWd())){
                resetPassWd = model.getResetPassWd();
            }

            GewayModel accountTpUpdate = new GewayModel();
            accountTpUpdate.setId(account.getId());
            if (!StringUtils.isBlank(passWd)){
                String md5PassWd = MD5.parseMD5(passWd);
                if (!GewayModel.getPassWd().equals(md5PassWd)){
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


            if (!StringUtils.isBlank(resetPassWd)){
                gewayService.updatePassWd(accountTpUpdate);
            }

            sendSuccessMessage(response, "保存成功~");
            return;
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }
}
