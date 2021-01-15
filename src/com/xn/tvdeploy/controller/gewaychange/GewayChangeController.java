package com.xn.tvdeploy.controller.gewaychange;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.controller.channelchange.ChannelChangeController;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.GewayChangeModel;
import com.xn.tvdeploy.model.ChannelModel;
import com.xn.tvdeploy.model.GewayChangeModel;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.ChannelChangeService;
import com.xn.tvdeploy.service.GewayChangeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 通道金额变更纪录的Controller层
 * @Author yoko
 * @Date 2021/1/15 10:56
 * @Version 1.0
 */
@Controller
@RequestMapping("/gewaychange")
public class GewayChangeController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelChangeController.class);

    @Autowired
    private GewayChangeService<GewayChangeModel> gewayChangeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/gewaychange/gewaychangeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, GewayChangeModel model) throws Exception {
        List<GewayChangeModel> dataList = new ArrayList<GewayChangeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setGewayId(account.getId());
                model.setIsShow(1);
            }
            dataList = gewayChangeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }

}
