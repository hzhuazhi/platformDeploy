package com.xn.tvdeploy.controller.channelout;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.*;
import com.xn.common.util.constant.CacheKey;
import com.xn.common.util.constant.CachedKeyUtils;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.*;
import com.xn.tvdeploy.service.*;
import com.xn.tvdeploy.util.PublicMethod;
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
    private RedisIdService redisIdService;

    @Autowired
    private ChannelOutService<ChannelOutModel> channelOutService;

    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;

    @Autowired
    private GewayService<GewayModel> gewayService;

    @Autowired
    private GewaytradetypeService<GewaytradetypeModel> gewaytradetypeService;

    @Autowired
    private ChannelgewayService<ChannelgewayModel> channelgewayService;

    @Autowired
    private ChannelBalanceDeductService<ChannelBalanceDeductModel> channelBalanceDeductService;


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
                model.setChannelId(account.getId());
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
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，不能操作
                model.setChannelId(account.getId());
            }
            channelOutModel = channelOutService.getTotalData(model);
//            if(channelOutModel!=null){
//                String   successRate= PublicMethod.accuracy(Double.parseDouble(channelOutModel.getSuccessCountMoney()),Double.parseDouble(channelOutModel.getCountTotalMoney()),2);
//                channelOutModel.setSuccessRate(successRate);
//            }
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
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_TP){
                sendFailureMessage(response,"管理员无法操作,请登录其它账号角色!");
                return null;
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelout/channeloutAdd";
    }


    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            bean.setTradeType("200001");
            // 判断交易类型是否为空
//            if (StringUtils.isBlank(bean.getTradeType())){
//                sendFailureMessage(response,"请填写交易类型!");
//                return;
//            }
            // 判断订单金额是否为空
            if (StringUtils.isBlank(bean.getTotalAmount())){
                sendFailureMessage(response,"请填写订单金额!");
                return;
            }else {
                // 判断订单金额是否包含减号
                if (bean.getTotalAmount().indexOf("-") > -1){
                    sendFailureMessage(response,"请填写正确的订单金额!");
                    return;
                }

                // 金额是否有效
                if (bean.getTotalAmount().indexOf(".") > -1){
                    boolean flag = StringUtil.isNumberByMoney(bean.getTotalAmount());
                    if (!flag){
                        sendFailureMessage(response,"金额小数点后只能有2位!");
                        return;
                    }
                }else {
                    boolean flag = StringUtil.isNumer(bean.getTotalAmount());
                    if (!flag){
                        sendFailureMessage(response,"请填写正常的数字的订单金额!");
                        return;
                    }
                    // 添加小数点后2位
                    bean.setTotalAmount(bean.getTotalAmount() + ".00");
                }
            }

            if (StringUtils.isBlank(bean.getBankName())){
                sendFailureMessage(response,"请填写银行名称!");
                return;
            }
            if (StringUtils.isBlank(bean.getBankCard())){
                sendFailureMessage(response,"请填写银行卡卡号!");
                return;
            }
            if (StringUtils.isBlank(bean.getAccountName())){
                sendFailureMessage(response,"请填写转账的银行开户人姓名!");
                return;
            }

            String trade_type = "";
            if (!StringUtils.isBlank(bean.getTradeType())){
                trade_type = bean.getTradeType();
            }

            // 获取渠道的信息
            AccountTpModel channelModel = new AccountTpModel();
            channelModel.setId(account.getId());
            channelModel = accountTpService.queryByCondition(channelModel);
            if (channelModel == null || channelModel.getId() <= 0){
                log.info("根据渠道ID查询渠道数据为空!");
                sendFailureMessage(response,"错误,请重试!");
                return;
            }

            // 判断渠道是否有余额
            if (StringUtils.isBlank(channelModel.getBalance())){
                sendFailureMessage(response,"您的余额不足!");
                return;
            }else {
                double d_balance = Double.parseDouble(channelModel.getBalance());
                double d_orderMoney = Double.parseDouble(bean.getTotalAmount());
                if (d_balance < d_orderMoney){
                    sendFailureMessage(response,"您的余额不足小于订单金额!");
                    return;
                }
            }

            // 渠道与通道的关联关系
            ChannelgewayModel channelGewayModel = null;

            // 通道信息
            GewayModel gewayModel = null;

            // 根据交易类型查询通道
            GewaytradetypeModel gewaytradetypeModel = null;
            if (!StringUtils.isBlank(trade_type)){
                // 根据交易类型查询通道
                GewaytradetypeModel gewaytradetypeQuery = new GewaytradetypeModel();
                gewaytradetypeQuery.setMyTradeType(bean.getTradeType());
                gewaytradetypeModel = gewaytradetypeService.queryByCondition(gewaytradetypeQuery);
                if (gewaytradetypeModel == null || gewaytradetypeModel.getId() <= 0){
                    sendFailureMessage(response,"请填写正确的支付类型!");
                    return;
                }

                // 查询通道信息
                GewayModel gewayQuery = new GewayModel();
                gewayQuery.setId(gewaytradetypeModel.getGewayId());
                gewayModel = gewayService.queryByCondition(gewayQuery);
                if (gewayModel == null || gewayModel.getId() <= 0){
                    sendFailureMessage(response,"请联系运营人员!");
                    return;
                }

                // 根据渠道ID加通道ID查询渠道与通道的关联关系
                ChannelgewayModel channelGewayQuery = new ChannelgewayModel();
                channelGewayQuery.setChannelId(channelModel.getId());
                channelGewayQuery.setGewayId(gewayModel.getId());
                channelGewayModel = channelgewayService.queryByCondition(channelGewayQuery);
                if (channelGewayModel == null || channelGewayModel.getId() <= 0){
                    sendFailureMessage(response,"请联系运营人员!");
                    return;
                }

            }else{
                // 查询此渠道下代收的通道集合
                ChannelgewayModel channelGewayQuery = PublicMethod.assembleChannelGewayQuery(0, channelModel.getId(), 0, 3, 1);
                List<ChannelgewayModel> channelGewayList = channelgewayService.queryAllList(channelGewayQuery);
                if (channelGewayList == null || channelGewayList.size() <= 0){
                    sendFailureMessage(response,"请联系运营人员!");
                    return;
                }
                // 从集合中按照比例筛选一个
                channelGewayModel = PublicMethod.ratioChannelGeway(channelGewayList);
                if (channelGewayModel == null || channelGewayModel.getId() <= 0){
                    sendFailureMessage(response,"请联系运营人员!");
                    return;
                }

                GewaytradetypeModel gewaytradetypeQuery = new GewaytradetypeModel();
                gewaytradetypeQuery.setGewayId(channelGewayModel.getGewayId());
                gewaytradetypeModel = gewaytradetypeService.queryByCondition(gewaytradetypeQuery);
                if (gewaytradetypeModel == null || gewaytradetypeModel.getId() <= 0){
                    sendFailureMessage(response,"请填写正确的支付类型!");
                    return;
                }

                // 查询通道信息
                GewayModel gewayQuery = new GewayModel();
                gewayQuery.setId(gewaytradetypeModel.getGewayId());
                gewayModel = gewayService.queryByCondition(gewayQuery);
                if (gewayModel == null || gewayModel.getId() <= 0){
                    sendFailureMessage(response,"请联系运营人员!");
                    return;
                }
            }
            if (StringUtils.isBlank(bean.getTradeType())){
                bean.setTradeType(gewaytradetypeModel.getMyTradeType());
            }


            // check校验请求的订单金额是否属于通道金额范围内
            boolean flag_money = PublicMethod.checkGewayMoneyRange(gewayModel.getMoneyType(), gewayModel.getMoneyRange(), bean.getTotalAmount());
            if (!flag_money){
                sendFailureMessage(response,"请按照规定输入金额!");
                return;
            }











            boolean sendFlag = false;// 请求结果：false表示请求失败，true表示请求成功
            // 计算手续费
            String serviceCharge = channelGewayModel.getServiceCharge();
            String extraServiceCharge = "";// 额外手续费
            if (channelGewayModel.getServiceChargeType() == 2){
                if (!StringUtils.isBlank(channelGewayModel.getExtraServiceCharge())){
                    extraServiceCharge = channelGewayModel.getExtraServiceCharge();
                }
            }


            // check 订单+手续费是否小于余额
            String totalMoney = "";
            String resMoney = StringUtil.getMultiply(bean.getTotalAmount(), serviceCharge);
            String money = StringUtil.getBigDecimalAdd(resMoney, bean.getTotalAmount());
            if (!StringUtils.isBlank(extraServiceCharge)){
                totalMoney = StringUtil.getBigDecimalAdd(money, extraServiceCharge);
            }

            double d_balance = Double.parseDouble(channelModel.getBalance());
            double d_orderMoney = Double.parseDouble(totalMoney);
            if (d_balance < d_orderMoney){
                sendFailureMessage(response,"您的余额不足小于订单金额!" + "余额：" + d_balance + " 订单加手续费需要：" + totalMoney);
                return;
            }



            String payCode = gewaytradetypeModel.getOutTradeType();
            // 我方订单号
            String myTradeNo = "SDDF" + DateUtil.getNowPlusTimeMill();
            String my_notify_url = gewayModel.getNotifyUrl();
            String nowTime = DateUtil.getNowPlusTime();
            //组装拼接
            if (gewayModel.getContacts().equals("CK")){


                // 组装请求蛋糕平台
                // 蛋糕
                Map<String ,Object> sendDataMap = new HashMap<>();
                sendDataMap.put("money", bean.getTotalAmount());
                sendDataMap.put("payType", payCode);
                sendDataMap.put("outTradeNo", myTradeNo);
                sendDataMap.put("secretKey", channelModel.getSecretKey());
                sendDataMap.put("notifyUrl", my_notify_url);
                sendDataMap.put("inBankCard", bean.getBankCard());
                sendDataMap.put("inBankName", bean.getBankName());
                sendDataMap.put("inAccountName", bean.getAccountName());
                sendDataMap.put("inBankSubbranch", bean.getBankSubbranch());
                sendDataMap.put("inBankProvince", "");
                sendDataMap.put("inBankCity", "");
                String parameter = JSON.toJSONString(sendDataMap);
                parameter = StringUtil.mergeCodeBase64(parameter);
                Map<String, String> sendMap = new HashMap<>();
                sendMap.put("jsonData", parameter);
                String sendData = JSON.toJSONString(sendMap);
                String fineData = HttpSendUtils.sendPostAppJson(gewayModel.getInterfaceAds(), sendData);
                Map<String, Object> resMap = new HashMap<>();
                Map<String, Object> dataMap = new HashMap<>();
                if (!StringUtils.isBlank(fineData)) {
                    resMap = JSON.parseObject(fineData, Map.class);
                    if (resMap.get("resultCode").equals("0")) {
                        sendFlag = true;
                    }
                }
                log.info("--------------fineData:" + fineData);
            }else if(gewayModel.getContacts().equals("HF")){
                Map<String ,Object> sendDataMap = new HashMap<>();
                sendDataMap.put("uid", gewayModel.getPayId());
                sendDataMap.put("addtime", String.valueOf(System.currentTimeMillis()));
                sendDataMap.put("out_trade_id", myTradeNo);
                sendDataMap.put("amount", bean.getTotalAmount());
                sendDataMap.put("bankcode", "unionpay");
                sendDataMap.put("bankuser", bean.getAccountName());
                sendDataMap.put("bankname", bean.getBankName());
                sendDataMap.put("bankno", bean.getBankCard());
                sendDataMap.put("notifyurl", my_notify_url);
                String sb = ASCIISort.getSign(sendDataMap, gewayModel.getSecretKey());
                sendDataMap.put("sign", sb);
                String sendData = JSON.toJSONString(sendDataMap);
                String resultData = HttpSendUtils.sendPostAppJson(gewayModel.getInterfaceAds(), sendData);
                Map<String, Object> resMap = new HashMap<>();
                if (!StringUtils.isBlank(resultData)) {
                    resMap = JSON.parseObject(resultData, Map.class);
                    if (Integer.parseInt(resMap.get("code").toString()) == 1) {
                        sendFlag = true;
                    }
                }
            }
            // start
            bean.setOutTradeNo(DateUtil.getNowPlusTimeMill());
            boolean flag = false;// 是否执行成功
            if (sendFlag){
                // 锁住要执行的渠道ID
                String lockKey = CachedKeyUtils.getCacheKey(CacheKey.LOCK_TASK_WORK_TYPE_CHANNEL, channelModel.getId());
                boolean flagLock = redisIdService.lock(lockKey);
                if (flagLock){
                    // 组装扣减渠道余额
                    AccountTpModel updateBalance = PublicMethod.assembleChannelBalance(channelModel.getId(), bean.getTotalAmount(), serviceCharge, extraServiceCharge);

                    // 组装添加渠道扣减余额的流水
                    ChannelBalanceDeductModel channelBalanceDeductModel = PublicMethod.assembleChannelBalanceDeduct(0, channelModel.getId(), myTradeNo, 2, updateBalance.getOrderMoney(),
                            0, null, null, 2);
                    // 组装渠道请求的代付订单信息
                    ChannelOutModel channelOutModel = PublicMethod.assembleChannelOutData(bean, myTradeNo, channelModel, channelModel.getId(), gewayModel.getId(),
                            channelGewayModel.getId(), channelGewayModel.getProfitType(), nowTime, my_notify_url, serviceCharge, updateBalance.getOrderMoney(),
                            updateBalance.getServiceChargeMoney(), sendFlag);

                    // 更新渠道余额
                    accountTpService.updateBalance(updateBalance);
                    // 添加渠道扣款流水
                    channelBalanceDeductService.add(channelBalanceDeductModel);
                    // 添加代付订单信息
                    channelOutService.add(channelOutModel);

                    // 解锁
                    redisIdService.delLock(lockKey);

                }

            }else {
                // 请求蛋糕平台失败：纪录请求的纪录，并且请求状态是失败状态
                ChannelOutModel channelOutModel = PublicMethod.assembleChannelOutData(bean, myTradeNo, channelModel, channelModel.getId(), gewayModel.getId(),
                        channelGewayModel.getId(), channelGewayModel.getProfitType(), nowTime, my_notify_url, serviceCharge, null,null, sendFlag);
                channelOutService.add(channelOutModel);
            }

            // end


            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }


    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                bean.setChannelId(account.getId());
            }
            bean.setSendNum(0);
            bean.setSendStatus(0);
            channelOutService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
