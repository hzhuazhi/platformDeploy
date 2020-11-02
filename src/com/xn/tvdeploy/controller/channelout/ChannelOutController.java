package com.xn.tvdeploy.controller.channelout;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelOutModel;
import com.xn.tvdeploy.model.ChannelgewayModel;
import com.xn.tvdeploy.model.GewayModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelOutService;
import com.xn.tvdeploy.service.ChannelgewayService;
import com.xn.tvdeploy.service.GewayService;
import com.xn.tvdeploy.util.PublicMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 渠道与通道的关联关系的Controller层
 * @Author yoko
 * @Date 2020/4/1 16:00
 * @Version 1.0
 */
@Controller
@RequestMapping("/channelOut")
public class ChannelOutController extends BaseController {

    private static Logger log = Logger.getLogger(ChannelOutController.class);

    @Autowired
    private ChannelOutService<ChannelOutModel> channelOutService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    @Autowired
    private GewayService<GewayModel> gewayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelout/channeloutIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelOutModel model) throws Exception {
        List<ChannelOutModel> dataList = new ArrayList<ChannelOutModel>();
        if (model.getBeginTime() == 0 || model.getEndTime() == 0){
            model.setBeginTime(  DateUtil.getDayNumber(new Date()));
            model.setEndTime(  DateUtil.getDayNumber(new Date()));
        }

        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = channelOutService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, ChannelOutModel model) throws Exception {
        ChannelOutModel channelOutModel = new ChannelOutModel();
        if (model.getBeginTime() == 0 || model.getEndTime() == 0){
            model.setBeginTime(  DateUtil.getDayNumber(new Date()));
            model.setEndTime(  DateUtil.getDayNumber(new Date()));
        }

        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelOutModel = channelOutService.getTotalData(model);
            if(channelOutModel!=null){
                String   successRate= PublicMethod.accuracy(Double.parseDouble(channelOutModel.getSuccessCountMoney()),Double.parseDouble(channelOutModel.getCountTotalMoney()),2);
                channelOutModel.setSuccessRate(successRate);
            }
        }
        HtmlUtil.writerJson(response, channelOutModel);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelOutModel model) throws Exception {
        List<ChannelOutModel> dataList = new ArrayList<ChannelOutModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，不能操作
//                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                HtmlUtil.writerJson(response, dataList);
            }
            dataList = channelOutService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
            }else {
                model.addAttribute("tp", accountTpService.queryAllList(new AccountTpModel()));
                model.addAttribute("geway", gewayService.queryAllList(new GewayModel()));
            }
//            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelOut/channelOutAdd";
    }


    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setSendNum(0);
            bean.setSendStatus(0);
            channelOutService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
