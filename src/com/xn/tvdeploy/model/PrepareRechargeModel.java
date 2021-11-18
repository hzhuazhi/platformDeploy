package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;


/**
 * @author yoko
 * @desc 代付预备充值表:预备给渠道进行充值的实体属性Bean
 * @create 2021-11-15 15:25
 **/
public class PrepareRechargeModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 名称/别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 渠道的主键ID
     *
     * @mbggenerated
     */
    private long channelId;

    /**
     * 我方订单号：变更涉及到的订单号
     *
     * @mbggenerated
     */
    private String myTradeNo;

    /**
     * 要变更的金额
     *
     * @mbggenerated
     */
    private String money;
    /**
     * 渠道名称
     *
     * @mbggenerated
     */
    private String channelName;

    /**
     * 变更金额类型：0初始化，1核减金额，2加金额
     *
     * @mbggenerated
     */
    private int changeType;

    /**
     * 证据截图
     *
     * @mbggenerated
     */
    private String pictureAds;

    /**
     * 数据是否展现给用户看：0初始化，1展现，2不展现
     *
     * @mbggenerated
     */
    private int isShow;

    /**
     * 数据说明：做解说用的
     *
     * @mbggenerated
     */
    private String dataExplain;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 补充数据的类型：1初始化，2补充数据失败（其它原因等..），3补充数据成功
     */
    private int workType;

    /**
     * 创建日期：存的日期格式20160530
     *
     * @mbggenerated
     */
    private int curday;

    /**
     * 创建所属小时：24小时制
     *
     * @mbggenerated
     */
    private int curhour;

    /**
     * 创建所属分钟：60分钟制
     *
     * @mbggenerated
     */
    private int curminute;

    /**
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private int runStatus;

    /**
     * 运行计算次数
     *
     * @mbggenerated
     */
    private int runNum;

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
     * 开始时间
     *
     * @mbggenerated
     */
    private int curdayStart;
    /**
     * 结束时间
     *
     * @mbggenerated
     */
    private int curdayEnd;

    /**
     * 补充数据的类型：1初始化，2补充数据失败（其它原因等..），3补充数据成功
     */
    private int checkWorkType;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getMyTradeNo() {
        return myTradeNo;
    }

    public void setMyTradeNo(String myTradeNo) {
        this.myTradeNo = myTradeNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public int getCurminute() {
        return curminute;
    }

    public void setCurminute(int curminute) {
        this.curminute = curminute;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
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


    public int getCheckWorkType() {
        return checkWorkType;
    }

    public void setCheckWorkType(int checkWorkType) {
        this.checkWorkType = checkWorkType;
    }
}
