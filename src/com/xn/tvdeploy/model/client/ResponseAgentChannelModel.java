package com.xn.tvdeploy.model.client;

import com.xn.common.page.BasePage;

/**
 * @author yoko
 * @desc 代理雨渠道的关联关系的实体属性Bean
 * @create 2021-11-18 16:41
 **/
public class ResponseAgentChannelModel extends BasePage {

    public ResponseAgentChannelModel(){

    }
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 代理的的主键ID
     */
    private long agentId;



    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 手续费类型：1固定值的手续费，2除了固定的手续费每单额外要收取手续费（额外手续费）
     */
    private int serviceChargeType;

    /**
     * 额外手续费值：每单还要收取额外的手续费；当字段service_charge_type等于2时，则要用到此字段
     */
    private String extraServiceCharge;


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
     * 代理名称
     */
    private String agentName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getServiceChargeType() {
        return serviceChargeType;
    }

    public void setServiceChargeType(int serviceChargeType) {
        this.serviceChargeType = serviceChargeType;
    }

    public String getExtraServiceCharge() {
        return extraServiceCharge;
    }

    public void setExtraServiceCharge(String extraServiceCharge) {
        this.extraServiceCharge = extraServiceCharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
