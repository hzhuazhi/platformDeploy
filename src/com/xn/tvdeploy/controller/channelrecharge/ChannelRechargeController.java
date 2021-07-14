package com.xn.tvdeploy.controller.channelrecharge;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.channelchange.ChannelChangeController;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.ChannelChangeModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description: 渠道查看余额充值变更的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/channelrecharge")
public class ChannelRechargeController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelChangeController.class);

    @Autowired
    private ChannelChangeService<ChannelChangeModel> channelChangeService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelrecharge/channelrechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel model) throws Exception {
        List<ChannelChangeModel> dataList = new ArrayList<ChannelChangeModel>();

        if (null==model.getCurdayStart()||model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
            model.setCurdayStart(DateUtil.getDayNumber(new Date()));
            model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setChannelId(account.getId());
                model.setIsShow(1);
            }
            dataList = channelChangeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
