package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 渠道与通道的关联关系的实体属性Bean
 * @Author yoko
 * @Date 2020/4/1 16:02
 * @Version 1.0
 */
public class ChannelgewayModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 渠道ID
     */
    private long channelId;

    /**
     * 通道的主键ID
     */
    private long gewayId;

    /**
     * 关联关系的名称
     */
    private String linkName;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 扣量比例
     */
    private int deductRatio;

    /**
     * 收益类型：1普通收益类型，2多人分配收益类型
     */
    private int profitType;


    /**
     * 通道代码定性类型：1初始化/无任何属性，2代收，3代付
     */
    private int gewayCodeType;

    /**
     * 每日成功金额是否到达上限：1初始化/未到达上限，2已到达上限
     */
    private int dayLimit;

    /**
     * 每日上限的成功金额
     */
    private String dayMoney;

    /**
     * 筛选比例
     */
    private int ratio;


    /**
     * 备注
     */
    private String remark;

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

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 通道名称
     */
    private String gewayName;

    /**
     * 筛选比例：开始
     */
    private int startRatio;

    /**
     * 筛选比例结束
     */
    private int endRatio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getGewayId() {
        return gewayId;
    }

    public void setGewayId(long gewayId) {
        this.gewayId = gewayId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getDeductRatio() {
        return deductRatio;
    }

    public void setDeductRatio(int deductRatio) {
        this.deductRatio = deductRatio;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public int getProfitType() {
        return profitType;
    }

    public void setProfitType(int profitType) {
        this.profitType = profitType;
    }

    public int getGewayCodeType() {
        return gewayCodeType;
    }

    public void setGewayCodeType(int gewayCodeType) {
        this.gewayCodeType = gewayCodeType;
    }

    public int getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(int dayLimit) {
        this.dayLimit = dayLimit;
    }

    public String getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(String dayMoney) {
        this.dayMoney = dayMoney;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public int getStartRatio() {
        return startRatio;
    }

    public void setStartRatio(int startRatio) {
        this.startRatio = startRatio;
    }

    public int getEndRatio() {
        return endRatio;
    }

    public void setEndRatio(int endRatio) {
        this.endRatio = endRatio;
    }
}
