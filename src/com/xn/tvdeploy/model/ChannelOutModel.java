package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class ChannelOutModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 我方订单号
     *
     * @mbggenerated
     */
    private String myTradeNo;

    /**
     * 渠道的主键ID
     *
     * @mbggenerated
     */
    private Long channelId;

    /**
     * 通道的主键ID
     *
     * @mbggenerated
     */
    private Long gewayId;

    /**
     * 渠道号/商铺号
     *
     * @mbggenerated
     */
    private String channel;

    /**
     * 交易类型
     *
     * @mbggenerated
     */
    private String tradeType;

    /**
     * 参数名称：商家订单金额，订单总金额
     *
     * @mbggenerated
     */
    private String totalAmount;

    /**
     * 手续费
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 实际金额
     *
     * @mbggenerated
     */
    private String actualMoney;

    /**
     * 订单状态：1初始化，2超时，3质疑，4成功
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * 参数名称：商家订单号
     *
     * @mbggenerated
     */
    private String outTradeNo;

    /**
     * 银行名称
     *
     * @mbggenerated
     */
    private String bankName;

    /**
     * 银行卡号
     *
     * @mbggenerated
     */
    private String bankCard;

    /**
     * 开户名
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 银行开户支行
     */
    private String bankSubbranch;

    /**
     * 银行卡转账图片凭证
     *
     * @mbggenerated
     */
    private String pictureAds;

    /**
     * 失败缘由
     *
     * @mbggenerated
     */
    private String failInfo;

    /**
     * 异步通知地址
     *
     * @mbggenerated
     */
    private String notifyUrl;

    /**
     * 我方异步通知地址
     *
     * @mbggenerated
     */
    private String myNotifyUrl;

    /**
     * 接口版本
     *
     * @mbggenerated
     */
    private String interfaceVer;

    /**
     * 参数名称：页面跳转同步通知地址；支付成功后，通过页面跳转的方式跳转到商家网站
     *
     * @mbggenerated
     */
    private String returnUrl;

    /**
     * 参数名称：回传参数商户如果支付请求是传递了该参数，则通知商户支付成功时会回传该参数
     *
     * @mbggenerated
     */
    private String extraReturnParam;

    /**
     * 客户端IP地址
     *
     * @mbggenerated
     */
    private String clientIp;

    /**
     * 参数名称：平台返回签名数据该参数用于验签
     *
     * @mbggenerated
     */
    private String sign;


    /**
     * 提交时间
     *
     * @mbggenerated
     */
    private Date subTime;

    /**
     * 商品名称
     *
     * @mbggenerated
     */
    private String productName;

    /**
     * 商品码
     *
     * @mbggenerated
     */
    private String productCode;
    /**
     * 渠道名称
     *
     * @mbggenerated
     */
    private String channelName;

    /**
     * 渠道与通道的关联关系的ID：对应表tb_hz_channel_geway的主键ID
     *
     * @mbggenerated
     */
    private Long channelGewayId;

    /**
     * 收益类型：1普通收益类型，2多人分配收益类型
     *
     * @mbggenerated
     */
    private Integer profitType;

    /**
     * 请求是否成功：1成功，2失败
     *
     * @mbggenerated
     */
    private Integer sendOk;

    /**
     * 审核状态：1初始化，2审核收款失败，3系统自动审核，4审核收款成功
     *
     * @mbggenerated
     */
    private Integer checkStatus;

    /**
     * 审核失败缘由，审核失败的原因
     *
     * @mbggenerated
     */
    private String checkInfo;

    /**
     * 开始时间
     *
     * @mbggenerated
     */
    private int beginTime;

    /**
     * 结束时间
     *
     * @mbggenerated
     */
    private int endTime;

    /**
     * 创建日期：存的日期格式20160530
     *
     * @mbggenerated
     */
    private Integer curday;

    /**
     * 创建所属小时：24小时制
     *
     * @mbggenerated
     */
    private Integer curhour;

    /**
     * 创建所属分钟：60分钟制
     *
     * @mbggenerated
     */
    private Integer curminute;

    /**
     * 运行计算次数
     *
     * @mbggenerated
     */
    private Integer runNum;

    /**
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private Integer runStatus;

    /**
     * 发送次数
     *
     * @mbggenerated
     */
    private Integer sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private Integer sendStatus;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private Date tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private Integer yn;
    /**
     * 订单总金额
     *
     * @mbggenerated
     */
    private String countTotalMoney;

    /**
     * 总金额
     *
     * @mbggenerated
     */
    private String countActualMoney;


    /**
     * 成功总金额
     *
     * @mbggenerated
     */
    private String successCountMoney;

    /**
     * 成功手续费
     *
     * @mbggenerated
     */
    private String successServiceChargeMoney;

    /**
     * 成功率
     *
     * @mbggenerated
     */
    private String successRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getGewayId() {
        return gewayId;
    }

    public void setGewayId(Long gewayId) {
        this.gewayId = gewayId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(String actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public String getFailInfo() {
        return failInfo;
    }

    public void setFailInfo(String failInfo) {
        this.failInfo = failInfo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMyNotifyUrl() {
        return myNotifyUrl;
    }

    public void setMyNotifyUrl(String myNotifyUrl) {
        this.myNotifyUrl = myNotifyUrl;
    }

    public String getInterfaceVer() {
        return interfaceVer;
    }

    public void setInterfaceVer(String interfaceVer) {
        this.interfaceVer = interfaceVer;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getExtraReturnParam() {
        return extraReturnParam;
    }

    public void setExtraReturnParam(String extraReturnParam) {
        this.extraReturnParam = extraReturnParam;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getChannelGewayId() {
        return channelGewayId;
    }

    public void setChannelGewayId(Long channelGewayId) {
        this.channelGewayId = channelGewayId;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public Integer getCurday() {
        return curday;
    }

    public void setCurday(Integer curday) {
        this.curday = curday;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getProfitType() {
        return profitType;
    }

    public void setProfitType(Integer profitType) {
        this.profitType = profitType;
    }

    public Integer getSendOk() {
        return sendOk;
    }

    public void setSendOk(Integer sendOk) {
        this.sendOk = sendOk;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCurhour() {
        return curhour;
    }

    public void setCurhour(Integer curhour) {
        this.curhour = curhour;
    }

    public Integer getCurminute() {
        return curminute;
    }

    public void setCurminute(Integer curminute) {
        this.curminute = curminute;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getCountTotalMoney() {
        return countTotalMoney;
    }

    public void setCountTotalMoney(String countTotalMoney) {
        this.countTotalMoney = countTotalMoney;
    }

    public String getCountActualMoney() {
        return countActualMoney;
    }

    public void setCountActualMoney(String countActualMoney) {
        this.countActualMoney = countActualMoney;
    }

    public String getSuccessCountMoney() {
        return successCountMoney;
    }

    public void setSuccessCountMoney(String successCountMoney) {
        this.successCountMoney = successCountMoney;
    }

    public String getSuccessServiceChargeMoney() {
        return successServiceChargeMoney;
    }

    public void setSuccessServiceChargeMoney(String successServiceChargeMoney) {
        this.successServiceChargeMoney = successServiceChargeMoney;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getBankSubbranch() {
        return bankSubbranch;
    }

    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }
}