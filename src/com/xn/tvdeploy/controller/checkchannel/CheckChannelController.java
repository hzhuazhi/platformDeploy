package com.xn.tvdeploy.controller.checkchannel;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.constant.CacheKey;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.model.CheckChannelModel;
import com.xn.tvdeploy.model.StrategyModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelService;
import com.xn.tvdeploy.service.CheckChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName:
 * @Description: 审核人关联的渠道：审核人员负责审核的渠道，进行关联关系的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/checkchannel")
public class CheckChannelController extends BaseController {

    private static Logger log = Logger.getLogger(CheckChannelController.class);


    @Autowired
    private CheckChannelService checkChannelService;

    @Autowired
    private AccountService<Account> accountService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;//渠道




    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/checkchannel/checkchannelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, CheckChannelModel model) throws Exception {
        List<CheckChannelModel> dataList = new ArrayList<CheckChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
//            model.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_CHECK);
//            List<Account> acDataList = accountService.queryByList(model);
//            for (Account at : acDataList){
//                // 查询此审核账号旗下的渠道
//                CheckChannelModel checkChannelQuery = new CheckChannelModel();
//                checkChannelQuery.setCheckAccountId(at.getId());
//                List<CheckChannelModel> checkChannelList = checkChannelService.queryAllList(checkChannelQuery);
//                if (checkChannelList != null && checkChannelList.size() > 0){
//                    String binding = "";
//                    for (CheckChannelModel cc : checkChannelList){
//                        binding += cc.getChannelName() + "#";
//                    }
//
//                    CheckChannelModel checkChannelModel = new CheckChannelModel();
//                    checkChannelModel.setCheckAccountId(at.getId());
//                    checkChannelModel.setAcName(at.getAcName());
//                    checkChannelModel.setBinding(binding);
//                    dataList.add(checkChannelModel);
//                }
//            }


            dataList = checkChannelService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, CheckChannelModel model) throws Exception {
        List<CheckChannelModel> dataList = new ArrayList<CheckChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = checkChannelService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 查询审核人的数据
        AccountModel accountQuery = new AccountModel();
        accountQuery.setRoleId(ManagerConstant.PUBLIC_CONSTANT.ROLE_CHECK);
        List<Account> acDataList = accountService.queryByList(accountQuery);
        List<CheckChannelModel> checkChannelList = checkChannelService.queryAllList();
        List<Long> notIdList = checkChannelList.stream().map(CheckChannelModel::getChannelId).collect(Collectors.toList());

        // 查询未与审核人员绑定的渠道
        AccountTpModel accountTpModel = new AccountTpModel();
        if (notIdList != null && notIdList.size() > 0){
            accountTpModel.setNotIdList(notIdList);
        }
        List<AccountTpModel> channelList = accountTpService.queryAllList(accountTpModel);
        model.addAttribute("acDataList", acDataList);
        model.addAttribute("channelList", channelList);

        return "manager/checkchannel/checkchannelAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, CheckChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            checkChannelService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        StrategyModel atModel = new StrategyModel();
        atModel.setId(id);
        model.addAttribute("account", checkChannelService.queryById(atModel));
        return "manager/checkchannel/checkchannelEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, CheckChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, CheckChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            checkChannelService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, CheckChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            checkChannelService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
