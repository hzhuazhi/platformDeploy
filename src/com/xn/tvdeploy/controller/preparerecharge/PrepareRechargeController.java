package com.xn.tvdeploy.controller.preparerecharge;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.common.util.StringUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.PrepareRechargeModel;
import com.xn.tvdeploy.model.StrategyModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.PrepareRechargeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author yoko
 * @desc 代付预备充值表:预备给渠道进行充值的Controller层
 * @create 2021-11-15 16:38
 **/
@Controller
@RequestMapping("/preparerecharge")
public class PrepareRechargeController extends BaseController {

    private static Logger log = Logger.getLogger(PrepareRechargeController.class);

    @Autowired
    private PrepareRechargeService<PrepareRechargeModel> prepareRechargeService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/preparerecharge/preparerechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel model) throws Exception {
        List<PrepareRechargeModel> dataList = new ArrayList<PrepareRechargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            if (model.getCurdayStart() == 0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            dataList = prepareRechargeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel model) throws Exception {
        List<PrepareRechargeModel> dataList = new ArrayList<PrepareRechargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = prepareRechargeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<AccountTpModel> tpList = new ArrayList<AccountTpModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            AccountTpModel queryTpModel = new AccountTpModel();
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                queryTpModel.setId(account.getId());
            }
            queryTpModel.setChannelType(3);
            tpList = accountTpService.queryAllList(queryTpModel);
            model.addAttribute("tpList", tpList);
        }
        return "manager/preparerecharge/preparerechargeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (bean == null){
                sendFailureMessage(response,"数据不能为空!");
                return;
            }
            if (bean.getChannelId() == 0){
                sendFailureMessage(response,"请选择渠道!");
                return;
            }
            if (StringUtils.isBlank(bean.getMoney())){
                sendFailureMessage(response,"请填写充值金额!");
                return;
            }else {

                // 判断订单金额是否包含减号
                if (bean.getMoney().indexOf("-") > -1){
                    sendFailureMessage(response,"请填写正确的充值金额!");
                    return;
                }
                boolean flag = StringUtil.isNumer(bean.getMoney());
                if (!flag){
                    sendFailureMessage(response,"请填写正常的数字的订单金额!");
                    return;
                }
            }

            String [] str_money = bean.getMoney().split("\\.");

            bean.setAlias("充值" + str_money[0]);
            bean.setMoney(str_money[0]);
            bean.setChangeType(2);
            bean.setMyTradeNo("充值");
            bean.setIsShow(1);
            bean.setCurday(DateUtil.getDayNumber(new Date()));
            bean.setCurhour(DateUtil.getHour(new Date()));
            bean.setCurminute(DateUtil.getCurminute(new Date()));
            prepareRechargeService.add(bean);

            String urlMoney = str_money[0] + ".00";
            String resData = HttpSendUtils.sendGet("http://www.rlk8.com/hcpay/pullhc/order.do?money=" + urlMoney + "&payType=&defaultBankNumber=", null, null);
            Map<String, String> map = new HashMap<>();
            map = JSON.parseObject(resData, Map.class);
            sendSuccessMessage(response, "保存成功~", map.get("msg"));
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        StrategyModel atModel = new StrategyModel();
        atModel.setId(id);
        model.addAttribute("account", prepareRechargeService.queryById(atModel));
        return "manager/preparerecharge/preparerechargeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            prepareRechargeService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            prepareRechargeService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            prepareRechargeService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }



    /**
     * @Description: 获取代付预备充值详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        PrepareRechargeModel query = new PrepareRechargeModel();
        query.setId(id);
        PrepareRechargeModel bean = prepareRechargeService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }





    /**
     * 审核预充值信息
     */
    @RequestMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response, PrepareRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response, "您无权审核信息!");
                return;
            }

            if (bean.getCheckWorkType() == 0){
                sendFailureMessage(response, "请填写审核状态!");
                return;
            }


            // check审核状态是否不是初始化状态
            PrepareRechargeModel query = new PrepareRechargeModel();
            query.setId(bean.getId());
            PrepareRechargeModel queryData = prepareRechargeService.queryById(query);
            if (queryData == null || queryData.getId() == 0){
                sendFailureMessage(response, "没有次条数据的记录!");
                return;
            }
            if (queryData.getWorkType() != 1){
                sendFailureMessage(response, "此条数据无需审核!");
                return;
            }

            // 更新审核状态
            bean.setWorkType(bean.getCheckWorkType());
            prepareRechargeService.update(bean);
            sendSuccessMessage(response, "保存成功~");
            return;
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }
}
