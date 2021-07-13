package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 通道的实体属性Bean
 * @Author yoko
 * @Date 2020/3/31 20:35
 * @Version 1.0
 */
public class GewayModel extends BasePage {
    /**
     * 自增主键ID
     */
    private long id;

    /**
     *登录账号
     */
    private String accountNum;

    /**
     * 账号密码
     */
    private String passWd;

    /**
     * 通道名称
     */
    private String gewayName;

    /**
     * 接口地址
     */
    private String interfaceAds;

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
     * 商铺号（商家号）
     */
    private String payId;

    /**
     * 总账
     */
    private String totalMoney;

    /**
     * 保底金额：预付款通道，如果余额少于保底金额，就不出码
     */
    private String leastMoney;

    /**
     * 余额
     */
    private String balance;


    /**
     * 秘钥key：分配给我方的秘钥
     */
    private String secretKey;

    /**
     * 回传标识
     */
    private String identify;

    /**
     * 同步的接口地址:我方的同步地址
     */
    private String notifyUrl;

    /**
     * 通道类型：1普通通道，2预付款通道
     */
    private int gewayType;

    /**
     * 支持金额类型：1固定的，2单一范围，3多个范围
     */
    private int moneyType;

    /**
     * 支持金额:money_type=1则50多个则以英文逗号风格，money_type=2则100-1000；money_type=3则100-1000,200-2000多个以英文逗号分割
     */
    private String moneyRange;

    /**
     * 所属角色ID
     */
    private long roleId;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     */
    private int isEnable;

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
     * 角色名
     */
    private String roleName;

    /**
     * 充值密码
     */
    private String resetPassWd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGewayName() {
        return gewayName;
    }

    public void setGewayName(String gewayName) {
        this.gewayName = gewayName;
    }

    public String getInterfaceAds() {
        return interfaceAds;
    }

    public void setInterfaceAds(String interfaceAds) {
        this.interfaceAds = interfaceAds;
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

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(String leastMoney) {
        this.leastMoney = leastMoney;
    }

    public int getGewayType() {
        return gewayType;
    }

    public void setGewayType(int gewayType) {
        this.gewayType = gewayType;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResetPassWd() {
        return resetPassWd;
    }

    public void setResetPassWd(String resetPassWd) {
        this.resetPassWd = resetPassWd;
    }

    public int getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(int moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyRange() {
        return moneyRange;
    }

    public void setMoneyRange(String moneyRange) {
        this.moneyRange = moneyRange;
    }
}
