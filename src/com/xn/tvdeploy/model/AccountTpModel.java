package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @author df
 * @Description:渠道账号的实体属性Bean
 * @create 2018-09-11 14:24
 **/
public class AccountTpModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     * 账号
     */
    private String accountNum;

    /**
     * 密码
     */
    private String passWd;

    /**
     * 提现密码
     */
    private String withdrawPassWd;

    private long roleId; //所属角色ID

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     *渠道号（商铺号）
     */
    private String channel;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系人电话
     */
    private String phoneNum;

    /**
     * 结算/支付方式：1对公，2对私
     */
    private int payType;

    /**
     * 总账
     */
    private String totalMoney;

    /**
     * 余额
     */
    private String balance;

    /**
     * 秘钥key
     */
    private String secretKey;

    /**
     * 是否需要谷歌验证：1不需要，2需要
     */
    private int isGoogle;

    /**
     * 谷歌唯一码
     */
    private String googleKey;

    /**
     * 锁定金额
     */
    private String lockMoney;

    /**
     * 同步的接口地址
     */
    private String lowerUrl;

    /**
     * 返回的成功标识
     */
    private String lowerSuc;

    /**
     * 是否需要数据同步:1需要同步，2不需要同步
     */
    private int isSynchro;

    /**
     * 提现类型：1默认在支付平台操作，2发送下发数据到水果平台
     */
    private int withdrawType;

    /**
     * 渠道类型：1代收，2大包，3代付
     */
    private int channelType;

    private int isEnable;

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
    private String roleName; //所属的角色名称

    /**
     * 归属代理
     */
    private long agentId;


    /**
     * 余额加
     */
    private String addBalance;

    /**
     * 余额减
     */
    private String subtractBalance;

    /**
     * 要进行更改的金额
     */
    private String orderMoney;


    /**
     * 密码-重置密码-新
     */
    private String resetPassWd;

    /**
     * 提现密码-重置提现密码-新
     */
    private String resetWithdrawPassWd;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getLowerUrl() {
        return lowerUrl;
    }

    public void setLowerUrl(String lowerUrl) {
        this.lowerUrl = lowerUrl;
    }

    public String getLowerSuc() {
        return lowerSuc;
    }

    public void setLowerSuc(String lowerSuc) {
        this.lowerSuc = lowerSuc;
    }

    public int getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(int isSynchro) {
        this.isSynchro = isSynchro;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }


    public int getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(int withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(String lockMoney) {
        this.lockMoney = lockMoney;
    }

    public String getAddBalance() {
        return addBalance;
    }

    public void setAddBalance(String addBalance) {
        this.addBalance = addBalance;
    }

    public String getSubtractBalance() {
        return subtractBalance;
    }

    public void setSubtractBalance(String subtractBalance) {
        this.subtractBalance = subtractBalance;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getWithdrawPassWd() {
        return withdrawPassWd;
    }

    public void setWithdrawPassWd(String withdrawPassWd) {
        this.withdrawPassWd = withdrawPassWd;
    }

    public String getResetPassWd() {
        return resetPassWd;
    }

    public void setResetPassWd(String resetPassWd) {
        this.resetPassWd = resetPassWd;
    }

    public String getResetWithdrawPassWd() {
        return resetWithdrawPassWd;
    }

    public void setResetWithdrawPassWd(String resetWithdrawPassWd) {
        this.resetWithdrawPassWd = resetWithdrawPassWd;
    }

    public int getIsGoogle() {
        return isGoogle;
    }

    public void setIsGoogle(int isGoogle) {
        this.isGoogle = isGoogle;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }
}
