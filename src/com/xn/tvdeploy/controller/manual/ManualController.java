package com.xn.tvdeploy.controller.manual;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.common.util.MD5Util;
import com.xn.common.util.StringUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.model.ManualModel;
import com.xn.tvdeploy.service.AccountTpService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.Logger;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description 代收订单的Controller层
 * <p>
 *     手动拉单
 * </p>
 * @Author yoko
 * @Date 2020/11/15 13:42
 * @Version 1.0
 */
@Controller
@RequestMapping("/manual")
public class ManualController extends BaseController {


    private static Logger log = Logger.getLogger(ManualController.class);

    public static String interfaceAds_POST = "http://localhost:8092/platform/action/dataCore";
//    public static String interfaceAds_POST = "http://47.116.98.162:8092/platform/action/dataCore";
//    public static String interfaceAds_GET = "http://47.116.98.162:8092/platform/get/dataCore?";

//    public static String interfaceAds_POST = "http://155.235.245.43:8092/platform/action/dataCore";
//    public static String interfaceAds_GET = "http://155.235.245.43:8092/platform/get/dataCore?";



    @Autowired
    private AccountTpService<AccountTpModel> accountTpService;



    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/manual/manualIndex";
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
            tpList = accountTpService.queryAllList(queryTpModel);
            ChannelModel dataModel = new ChannelModel();
            model.addAttribute("tpList", tpList);
        }
        return "manager/manual/manualAdd";
    }



    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ManualModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (bean == null || bean.getPayType() == 0){
//                sendFailureMessage(response,"请填写交易类型，跟订单金额!");
//                return;
//            }
            String channel = "";
            String trade_type = "";
            String total_amount = "";
            String out_trade_no = "SDDS" + DateUtil.getNowPlusTimeMill();
            String sign = "";
            String notify_url = "http://www.baidu.com/temp";
            String interface_ver = "V5.0";
            String return_url = "http://www.qidian.com";
            String noredirect = "1";
            if (!StringUtils.isBlank(bean.getTradeType())){
                trade_type = bean.getTradeType();
            }
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
                        log.info("");
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
            total_amount = bean.getTotalAmount();
            // 获取渠道的信息
            AccountTpModel channelModel = new AccountTpModel();
            channelModel.setId(bean.getChannelId());
            channelModel = accountTpService.queryByCondition(channelModel);
            if (channelModel == null || channelModel.getId() <= 0){
                log.info("根据渠道ID查询渠道数据为空!");
                sendFailureMessage(response,"错误,请重试!");
                return;
            }
            channel = channelModel.getChannel();

            sign = getQrCodeSign(channelModel.getChannel(), trade_type, total_amount, out_trade_no, notify_url, channelModel.getSecretKey());

            Map<String ,Object> sendDataMap = new HashMap<>();
            sendDataMap.put("channel", channel);
            sendDataMap.put("trade_type", trade_type);
            sendDataMap.put("total_amount", total_amount);
            sendDataMap.put("sign", sign);
            sendDataMap.put("out_trade_no", out_trade_no);
            sendDataMap.put("notify_url", notify_url);
            sendDataMap.put("interface_ver", interface_ver);
            sendDataMap.put("return_url", return_url);
            sendDataMap.put("noredirect", noredirect);
            String parameter = JSON.toJSONString(sendDataMap);
            String resData = HttpSendUtils.sendPostAppJson(interfaceAds_POST, parameter);
            if (StringUtils.isBlank(resData)){
                sendFailureMessage(response,"拉单失败,请您重试!");
                return;
            }
            if (!StringUtils.isBlank(resData)){
                if (resData.indexOf("code") > -1){
                    log.info("手动拉单失败,resData:" + resData);
                    sendFailureMessage(response,"拉单失败,请您重试!");
                    return;
                }
            }
            String qrUrl = StringUtil.decoderBase64(resData);
            sendSuccessMessage(response, "保存成功~", qrUrl);
            return;
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
            return;
        }
    }


    public static String getQrCodeSign(String channel, String trade_type, String total_amount, String out_trade_no, String notify_url, String secretKey){
        String checkSign = "";
        if (!StringUtils.isBlank(trade_type)){
            checkSign = "channel=" + channel + "&" + "trade_type=" + trade_type + "&" + "total_amount=" + total_amount
                    + "&" + "out_trade_no=" + out_trade_no + "&" + "notify_url=" + notify_url + "&" + "key=" + secretKey ;
        }else {
            checkSign = "channel=" + channel + "&" + "total_amount=" + total_amount
                    + "&" + "out_trade_no=" + out_trade_no + "&" + "notify_url=" + notify_url + "&" + "key=" + secretKey ;
        }
        String str = MD5Util.encryption(checkSign);
        return str;
    }

}
