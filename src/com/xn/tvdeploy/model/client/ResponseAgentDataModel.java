package com.xn.tvdeploy.model.client;

import com.xn.common.page.BasePage;

/**
 * @Description 代理-订单信息-收益的实体属性Bean
 * @Author yoko
 * @Date 2020/5/6 0:11
 * @Version 1.0
 */
public class ResponseAgentDataModel extends BasePage {
    public ResponseAgentDataModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 我方订单号
     */
    private String myTradeNo;

    /**
     * 平台订单号
     */
    private String tradeNo;

    /**
     * 参数名称：商家订单金额，订单总金额
     */
    private String totalAmount;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 实际金额
     */
    private String actualMoney;

    /**
     * 实际支付金额
     */
    private String payAmount;

    /**
     * 实际支付金额后扣了收付费后的金额
     */
    private String payActualMoney;


    /**
     * 代理主键ID
     */
    private long agentId;

    /**
     * 是否补单
     */
    private int replenishType;


    /**
     * 收益占比
     */
    private String profitRatio;

    /**
     * 实际收益
     */
    private String profit;

    /**
     * 收益类型：1订单手续费的收益，2固定每单收取的收益：比如每单收取1元。。。（固定分成，额外分成）
     */
    private int profitType;



    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

    private int curdayStart;
    private int curdayEnd;

    /**
     * 代理名称
     */
    private String agentName;


    private String totalProfit;

    private String totalMoney;

    private String profitTypeStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayActualMoney() {
        return payActualMoney;
    }

    public void setPayActualMoney(String payActualMoney) {
        this.payActualMoney = payActualMoney;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public int getReplenishType() {
        return replenishType;
    }

    public void setReplenishType(int replenishType) {
        this.replenishType = replenishType;
    }

    public String getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(String profitRatio) {
        this.profitRatio = profitRatio;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public int getProfitType() {
        return profitType;
    }

    public void setProfitType(int profitType) {
        this.profitType = profitType;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public int getCurdayStart() {
        return curdayStart;
    }

    public void setCurdayStart(int curdayStart) {
        this.curdayStart = curdayStart;
    }

    public int getCurdayEnd() {
        return curdayEnd;
    }

    public void setCurdayEnd(int curdayEnd) {
        this.curdayEnd = curdayEnd;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getProfitTypeStr() {
        return profitTypeStr;
    }

    public void setProfitTypeStr(String profitTypeStr) {
        this.profitTypeStr = profitTypeStr;
    }
}
