package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @ClassName:
 * @Description: 审核人关联的渠道：审核人员负责审核的渠道，进行关联关系的实体属性Bean
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class CheckChannelModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 审核人员的账号ID
     *
     * @mbggenerated
     */
    private long checkAccountId;

    /**
     * 渠道的主键ID
     *
     * @mbggenerated
     */
    private long channelId;

    /**
     * 绑定
     *
     * @mbggenerated
     */
    private String binding;


    /**
     * 创建人ID
     *
     * @mbggenerated
     */
    private long createUserId;

    /**
     * 创建人归属角色ID
     *
     * @mbggenerated
     */
    private long createRoleId;

    /**
     * 更新人ID
     *
     * @mbggenerated
     */
    private long updateUserId;

    /**
     * 更新人归属角色ID
     *
     * @mbggenerated
     */
    private long updateRoleId;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private String tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private int yn;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 审核人员名称
     */
    private String acName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCheckAccountId() {
        return checkAccountId;
    }

    public void setCheckAccountId(long checkAccountId) {
        this.checkAccountId = checkAccountId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public long getCreateRoleId() {
        return createRoleId;
    }

    public void setCreateRoleId(long createRoleId) {
        this.createRoleId = createRoleId;
    }

    public long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public long getUpdateRoleId() {
        return updateRoleId;
    }

    public void setUpdateRoleId(long updateRoleId) {
        this.updateRoleId = updateRoleId;
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

    public String getTsTime() {
        return tsTime;
    }

    public void setTsTime(String tsTime) {
        this.tsTime = tsTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }
}
