package com.xn.tvdeploy.controller.channelchange;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.*;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.BatchService;
import com.xn.tvdeploy.service.ChannelChangeService;
import com.xn.tvdeploy.service.ChannelService;
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
 * @Description:补收益表的Controller层
 * @create 2018-09-13 16:04
 **/
@Controller
@RequestMapping("/channelchange")
public class ChannelChangeController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelChangeController.class);

    @Autowired
    private ChannelChangeService<ChannelChangeModel> channelChangeService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelchange/channelchangeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel model) throws Exception {
        List<ChannelChangeModel> dataList = new ArrayList<ChannelChangeModel>();

        if (null==model.getCurdayStart()||model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
            model.setCurdayStart(DateUtil.getDayNumber(new Date()));
            model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                HtmlUtil.writerJson(response, model.getPage(), dataList);
//                model.setId(account.getId());
            }
            dataList = channelChangeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }

    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel model) throws Exception {
        List<ChannelChangeModel> dataList = new ArrayList<ChannelChangeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = channelChangeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
//        List<AccountTpModel> tpList = new ArrayList<AccountTpModel>();
//        AccountTpModel queryTpModel = new AccountTpModel();
//        queryTpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
//        List<ChannelModel> chList = new ArrayList<ChannelModel>();
//        ChannelModel queryChannelModel = new ChannelModel();
//        queryChannelModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
//        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                //不是管理员，只能查询自己的数据
//                queryTpModel.setId(account.getId());
//                queryChannelModel.setTpId(account.getId());
//            }
//            chList = channelChangeService.queryAllList(queryChannelModel);
//        }
//        model.addAttribute("tpList", tpList);
//        model.addAttribute("chList", chList);
        model.addAttribute("tp", accountTpService.queryAllList(new AccountTpModel()));
        return "manager/channelchange/channelchangeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()!=1){
                sendFailureMessage(response, "只允许管理员操作~");
                return;
            }
            bean.setCreateRoleId(account.getRoleId());
            bean.setCreateUserId(account.getId());
            bean.setCurday(DateUtil.getDayNumber(new Date()));
            bean.setCurhour(DateUtil.getHour(new Date()));
            bean.setCurminute(DateUtil.getCurminute(new Date()));
            channelChangeService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(HttpServletRequest request, HttpServletResponse response, Model model, long id) {
        List<ChannelChangeModel> tpList = new ArrayList<ChannelChangeModel>();
        ChannelChangeModel queryTpModel = new ChannelChangeModel();
//        queryTpModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        ChannelModel chModel = new ChannelModel();
        chModel.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
            }
            tpList = channelChangeService.queryAllList(queryTpModel);
        }
        return "manager/channelchange/channelchangeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelChangeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            channelChangeService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelChangeService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            bean.setUpdateUser(account.getId());
//            bean.setUpdateRole(account.getRoleId());
            channelChangeService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
