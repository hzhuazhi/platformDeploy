package com.xn.tvdeploy.util;

import com.alibaba.fastjson.JSON;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.MD5;
import com.xn.common.util.StringUtil;
import com.xn.tvdeploy.model.*;
import com.xn.tvdeploy.util.call.model.ts.res.AdsModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author df
 * @Description: TODO(公共方法类)
 * @create 10:12 2018/9/12
 **/
public class PublicMethod{
    private static Logger log = Logger.getLogger(PublicMethod.class);

    /**
     * @Description: TODO(生成AppKey的值)
     * @author df
     * @param dpId-开发者ID
     * @create 10:30 2018/9/12
     **/
    public static String assembleAppKey(long dpId) throws Exception {
        String str = "";
        String dp = dpId+"";
        String timeMill = DateUtil.getNowPlusTimeMill();
        str = MD5.parseMD5(dp+timeMill);
        return str;
    }

    /**
     * @Description: TODO(组装出渠道号集合的所有主键ID)
     * @author df
     * @param list-渠道号数据集合
     * @create 23:33 2018/9/13
     **/
    public static List<Long> assembleChannelId(List<ChannelModel> list){
        List<Long> idList = list.stream().map(ChannelModel::getId).collect(Collectors.toList());
        return idList;
    }

    /**
     * @Description: TODO(生成UUID)
     * @author df
     * @create 16:56 2018/9/18
     **/
    public static String assembleUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * @Description: TODO(组装查询广告主档的查询条件)
     * @author df
     * @param sourceId-第三方广告的ID
     * @param sourceType-源标识类型
     * @create 14:52 2018/10/15
     **/
    public static OfferMModel assembleQueryOfferMM(String sourceId, String sourceType){
        OfferMModel resBean = new OfferMModel();
        resBean.setSourceId(sourceId);
        resBean.setSourceType(sourceType);
        return resBean;
    }

    /**
     * @Description: TODO(组装探索的广告信息，组装成我方想要的字段，进行入库)
     * @author df
     * @param apiId-Api的主键ID
     * @param adsModel-探索的广告信息
     * @param sourceType-探索的源类型：探索=SRE_TS
     * @param warehousingType-入库类型：0初始化，1work调用接口，2用户实时调用，3手动录入
     * @create 15:28 2018/10/15
     **/
    public static OfferMModel assembleAddOfferMByTs(long apiId, AdsModel adsModel, String sourceType, int warehousingType){
        OfferMModel resBean = new OfferMModel();
        resBean.setApiId(apiId);
        resBean.setSourceId(adsModel.getCid());
        resBean.setSourceType(sourceType);
        resBean.setSpreadUrl(adsModel.getAdurl());
        resBean.setPayout(adsModel.getPrice());

        if (!StringUtils.isBlank(adsModel.getAdmt())){
            if (adsModel.getAdmt().equals(1)){
                resBean.setOfferType(2);
            }else if (adsModel.getAdmt().equals(2)){
                resBean.setOfferType(3);
            }else if (adsModel.getAdmt().equals(3)){
                resBean.setOfferType(4);
            }else if (adsModel.getAdmt().equals(4)){
                resBean.setOfferType(5);
            }else if (adsModel.getAdmt().equals(5)){
                resBean.setOfferType(6);
            }else if (adsModel.getAdmt().equals(6)){
                resBean.setOfferType(7);
            }else if (adsModel.getAdmt().equals(7)){
                resBean.setOfferType(8);
            }else if (adsModel.getAdmt().equals(8)){
                resBean.setOfferType(9);
            }else{
                resBean.setOfferType(1);
            }
        }

        if (!StringUtils.isBlank(adsModel.getAdct())){
            if(adsModel.getAdct().equals(1)){
                resBean.setOfferClickType(2);
            }else if(adsModel.getAdct().equals(2)){
                resBean.setOfferClickType(3);
            }else if(adsModel.getAdct().equals(4)){
                resBean.setOfferClickType(4);
            }else if(adsModel.getAdct().equals(8)){
                resBean.setOfferClickType(5);
            }else if(adsModel.getAdct().equals(16)){
                resBean.setOfferClickType(6);
            }else if(adsModel.getAdct().equals(32)){
                resBean.setOfferClickType(7);
            }else if(adsModel.getAdct().equals(64)){
                resBean.setOfferClickType(8);
            }else{
                resBean.setOfferClickType(1);
            }
        }

        resBean.setWarehousingType(warehousingType);
        resBean.setIsHandle(ManagerConstant.PUBLIC_CONSTANT.IS_HANDLE_XYCL);
        resBean.setHandleStatus(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE);

        OfferDModel offerDModel = new OfferDModel();
        offerDModel.setResUrl(adsModel.getAdi());
        offerDModel.setOfferJson(JSON.toJSONString(adsModel));

        resBean.setOfferDModel(offerDModel);
        return resBean;
    }


    /**
     * @Description: TODO(组装广告详情数据)
     * @author df
     * @param offerMId-广告主档的主键ID
     * @create 15:40 2018/10/15
     **/
    public static OfferDModel assembleOfferD(long offerMId, OfferDModel model){
        OfferDModel resBean = new OfferDModel();
        resBean = model;
        resBean.setId(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO);
        resBean.setOfferMId(offerMId);
        return resBean;
    }

    /**
     * @Description: 组装渠道上报的数据-补单
     * @param tpDataInfoModel
     * @return
     * @author yoko
     * @date 2020/8/6 16:15
    */
    public static TpDataInfoModel assembleChannelData(TpDataInfoModel tpDataInfoModel) throws Exception{
        TpDataInfoModel resBean = new TpDataInfoModel();
        resBean = tpDataInfoModel;

        resBean.setId(0);
        String orderNo = DateUtil.getNowLongTime();
        resBean.setMyTradeNo(orderNo);
        if (StringUtils.isBlank(tpDataInfoModel.getOutTradeNo())){
            resBean.setOutTradeNo(orderNo);
        }
        resBean.setSubTime(DateUtil.getNowPlusTime());

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;

    }

    public static TpDataInfoModel assembleDataCore(TpDataInfoModel tpDataInfoModel, String myTradeNo) throws Exception{
        TpDataInfoModel resBean = new TpDataInfoModel();
        resBean = tpDataInfoModel;
        resBean.setId(0);

        if (!StringUtils.isBlank(myTradeNo)){
            resBean.setMyTradeNo(myTradeNo);
        }
        resBean.setTradeNo(DateUtil.getNowLongTime());
        if (StringUtils.isBlank(resBean.getPayAmount())){
            // 表示页面没填写，说明支付金额一致
            resBean.setPayAmount(resBean.getTotalAmount());
            resBean.setPayActualMoney(resBean.getActualMoney());
            resBean.setMoneyFitType(4);
        }else {
            // 表示页面填写了实际金额：如果填写了实际金额则表示实际支付金额与订单金额不一致
            String payActualMoney = "";
            payActualMoney = StringUtil.getMultiply(resBean.getPayAmount(), resBean.getServiceCharge());
            payActualMoney = StringUtil.getBigDecimalSubtractStr(resBean.getPayAmount(), payActualMoney);
            resBean.setPayActualMoney(payActualMoney);
        }

        resBean.setTradeStatus(1);
//        resBean.setExtraReturnParam("");
        resBean.setTradeTime(DateUtil.getNowPlusTime());
        if (!StringUtils.isBlank(resBean.getExtraReturnParam())){
            resBean.setXyExtraReturnParam(resBean.getExtraReturnParam());
        }
        resBean.setReplenishType(2);

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;
    }


    public static String accuracy(double num, double total, int scale){
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num)+"%";
    }

    /**
     * @Description: 组装扣减渠道余额
     * @param id - 主键ID
     * @param orderMoney - 订单金额
     * @param serviceCharge - 手续费
     * @param extraServiceCharge - 额外手续费
     * @return com.hz.platform.master.core.model.channel.ChannelModel
     * @author yoko
     * @date 2020/10/31 19:40
     */
    public static AccountTpModel assembleChannelBalance(long id, String orderMoney, String serviceCharge, String extraServiceCharge){
        AccountTpModel resBean = new AccountTpModel();
        resBean.setId(id);
        // 计算要扣的金额
        String resMoney = StringUtil.getMultiply(orderMoney, serviceCharge);
        String money = StringUtil.getBigDecimalAdd(resMoney, orderMoney);
        if (!StringUtils.isBlank(extraServiceCharge)){
            money = StringUtil.getBigDecimalAdd(money, extraServiceCharge);
        }
        resBean.setSubtractBalance("1");
        resBean.setOrderMoney(money);
        String serviceChargeMoney = "";
        serviceChargeMoney = StringUtil.getMultiply(orderMoney, serviceCharge);
        if (!StringUtils.isBlank(extraServiceCharge)){
            serviceChargeMoney = StringUtil.getBigDecimalAdd(serviceChargeMoney, extraServiceCharge);
        }
        resBean.setServiceChargeMoney(serviceChargeMoney);
        return resBean;
    }


    /**
     * @Description: 组装添加流水或查询流水的方法
     * @param id - 主键ID
     * @param channelId - 渠道ID
     * @param orderNo - 订单号
     * @param orderType - 订单类型：1代收，2代付
     * @param money - 订单金额
     * @param orderStatus - 订单状态：1初始化，2超时/失败，3有质疑，4成功，5表示订单超时且操作状态属于初始化的
     * @param delayTime - 延迟运行时间：当订单属于超时状态：则系统时间需要大于此时间才能进行逻辑操作
     * @param lockTime - 锁定时间
     * @param type - 操作类型：1查询，2添加数据
     * @return com.hz.cake.master.core.model.merchant.MerchantBalanceDeductModel
     * @author yoko
     * @date 2020/10/30 20:21
     */
    public static ChannelBalanceDeductModel assembleChannelBalanceDeduct(long id, long channelId, String orderNo, int orderType, String money, int orderStatus,
                                                                         String delayTime, String lockTime, int type){
        ChannelBalanceDeductModel resBean = new ChannelBalanceDeductModel();
        if (id > 0){
            resBean.setId(id);
        }
        if (channelId > 0){
            resBean.setChannelId(channelId);
        }
        if (!StringUtils.isBlank(orderNo)){
            resBean.setOrderNo(orderNo);
        }
        if (orderType > 0){
            resBean.setOrderType(orderType);
        }
        if (!StringUtils.isBlank(money)){
            resBean.setMoney(money);
        }
        if (orderStatus > 0){
            resBean.setOrderStatus(orderStatus);
        }
        if (!StringUtils.isBlank(delayTime)){
            resBean.setDelayTime(delayTime);
        }else {
            String delayTimeStr = DateUtil.addDateMinute(30);
            resBean.setDelayTime(delayTimeStr);
        }
        if (!StringUtils.isBlank(lockTime)){
            resBean.setLockTime(lockTime);
        }
        if (type > 0){
            if (type == 2){
                resBean.setCurday(DateUtil.getDayNumber(new Date()));
                resBean.setCurhour(DateUtil.getHour(new Date()));
                resBean.setCurminute(DateUtil.getCurminute(new Date()));
            }
        }
        return resBean;
    }




    /**
     * @Description: 组装渠道代付订单数据
     * @param requestData - 请求的基础数据
     * @param myTradeNo - 我方订单号
     * @param channelId - 渠道主键ID
     * @param gewayId - 通道主键ID
     * @param channelGewayId - 渠道与通道的关联关系的ID：对应表tb_hz_channel_geway的主键ID
     * @param profitType - 收益类型：1普通收益类型，2多人分配收益类型
     * @param nowTime - 现在时间
     * @param serviceCharge - 手续费
     * @param serviceChargeMoney - 手续费具体金额
     * @param sendFlag - false表示请求失败，true表示请求成功
     * @return ChannelDataModel
     * @author yoko
     * @date 2020/3/24 21:41
     */
    public static ChannelOutModel assembleChannelOutData(ChannelOutModel requestData, String myTradeNo, AccountTpModel channelModel, long channelId, long gewayId,
                                                         long channelGewayId, int profitType, String nowTime, String my_notify_url, String serviceCharge, String actualMoney,
                                                         String serviceChargeMoney, boolean sendFlag) throws Exception{
        ChannelOutModel resBean = new ChannelOutModel();
        resBean.setMyTradeNo(myTradeNo);
        resBean.setChannelId(channelId);
        resBean.setGewayId(gewayId);
        resBean.setChannel(channelModel.getChannel());
        resBean.setChannelGewayId(channelGewayId);
        resBean.setProfitType(profitType);
        resBean.setTradeType(requestData.getTradeType());
        resBean.setTotalAmount(requestData.getTotalAmount());
        resBean.setServiceCharge(serviceCharge);
        if (!StringUtils.isBlank(actualMoney)){
            resBean.setActualMoney(actualMoney);
        }
        resBean.setServiceChargeMoney(serviceChargeMoney);
        resBean.setOutTradeNo(requestData.getOutTradeNo());
        resBean.setBankName(requestData.getBankName());
        resBean.setBankCard(requestData.getBankCard());
        resBean.setAccountName(requestData.getAccountName());
        if (!StringUtils.isBlank(requestData.getBankSubbranch())){
            resBean.setBankSubbranch(requestData.getBankSubbranch());
        }
//        if (!StringUtils.isBlank(requestData.bank_province)){
//            resBean.setBankProvince(requestData.bank_province);
//        }
//        if (!StringUtils.isBlank(requestData.bank_city)){
//            resBean.setBankCity(requestData.bank_city);
//        }
        if (!StringUtils.isBlank(requestData.getNotifyUrl())){
            resBean.setNotifyUrl(requestData.getNotifyUrl());
        }
        resBean.setMyNotifyUrl(my_notify_url);
        if (!StringUtils.isBlank(requestData.getInterfaceVer())){
            resBean.setInterfaceVer(requestData.getInterfaceVer());
        }
        if (!StringUtils.isBlank(requestData.getReturnUrl())){
            resBean.setReturnUrl(requestData.getReturnUrl());
        }
        if (!StringUtils.isBlank(requestData.getExtraReturnParam())){
            resBean.setExtraReturnParam(requestData.getExtraReturnParam());
        }
        if (!StringUtils.isBlank(requestData.getClientIp())){
            resBean.setClientIp(requestData.getClientIp());
        }
//        resBean.setSign(requestData.sign);
        resBean.setSubTime(DateUtil.fromStringToDate(new Date()));
        if (!StringUtils.isBlank(requestData.getProductName())){
            resBean.setProductName(requestData.getProductName());
        }
        if (!StringUtils.isBlank(requestData.getProductCode())){
            resBean.setProductCode(requestData.getProductCode());
        }
        if (sendFlag){
            resBean.setSendOk(1);
        }else {
            resBean.setSendOk(2);
        }
        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;

    }


    /**
     * @Description: 组装查询渠道与通道的关联关系的查询方法
     * @param id - 主键ID
     * @param channelId - 渠道主键ID
     * @param gewayId - 通道ID
     * @param gewayCodeType - 通道代码定性类型：1初始化/无任何属性，2代收，3代付
     * @param dayLimit - 每日成功金额是否到达上限：1初始化/未到达上限，2已到达上限
     * @return com.hz.platform.master.core.model.channelgeway.ChannelGewayModel
     * @author yoko
     * @date 2020/11/25 16:53
     */
    public static ChannelgewayModel assembleChannelGewayQuery(long id, long channelId, long gewayId, int gewayCodeType, int dayLimit){
        ChannelgewayModel resBean = new ChannelgewayModel();
        if (id > 0){
            resBean.setId(id);
        }
        if (channelId > 0){
            resBean.setChannelId(channelId);
        }
        if (gewayId > 0){
            resBean.setGewayId(gewayId);
        }
        if (gewayCodeType > 0){
            resBean.setGewayCodeType(gewayCodeType);
        }
        if (dayLimit > 0){
            resBean.setDayLimit(dayLimit);
        }
        return resBean;
    }


    /**
     * @Description: 通过比例筛选出一条关联关系
     * @param channelGewayList - 渠道与通道的关联关系
     * @return
     * @author yoko
     * @date 2020/11/25 17:08
     */
    public static ChannelgewayModel ratioChannelGeway(List<ChannelgewayModel> channelGewayList){
        int start = 0;
        int end = 0;
        for (int i = 0; i < channelGewayList.size(); i++){
            start = end;
            end += channelGewayList.get(i).getRatio();
            channelGewayList.get(i).setStartRatio(start);
            channelGewayList.get(i).setEndRatio(end);
        }
        int random = new Random().nextInt(end);
        for (ChannelgewayModel channelGewayModel : channelGewayList){
            log.info("id:"+ channelGewayModel.getId() + ",ratio:" + channelGewayModel.getRatio() + ",start:" + channelGewayModel.getStartRatio() + ",end:" + channelGewayModel.getEndRatio());
            if (random >= channelGewayModel.getStartRatio() && random < channelGewayModel.getEndRatio()){
                return channelGewayModel;
            }
        }
        return null;

    }




    public static void main(String[] args) throws Exception {
        String str = assembleAppKey(3);
        log.info("str:"+str);
    }
}
