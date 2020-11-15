package com.xn.tvdeploy.model;

import com.xn.common.page.BasePage;

/**
 * @Description 手动拉单
 * @Author yoko
 * @Date 2020/11/15 13:50
 * @Version 1.0
 */
public class ManualModel extends BasePage {

    /**
     * 支付类型：0初始化，1支付宝转卡，2银行卡转卡
     */
    private int payType;

    /**
     * 订单金额
     */
    private String totalAmount;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
