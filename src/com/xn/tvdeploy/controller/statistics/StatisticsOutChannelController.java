package com.xn.tvdeploy.controller.statistics;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.StatisticsOutChannelModel;
import com.xn.tvdeploy.service.StatisticsOutChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description: 统计代付渠道的每日记录的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/statisticsOutChannel")
public class StatisticsOutChannelController extends BaseController {
    private static Logger log = Logger.getLogger(StatisticsOutChannelController.class);

    @Autowired
    private StatisticsOutChannelService<StatisticsOutChannelModel> statisticsOutChannelService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/statistics/statisticsOutChannelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, StatisticsOutChannelModel model) throws Exception {
        List<StatisticsOutChannelModel> dataList = new ArrayList<StatisticsOutChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                log.info("6");
                return;
            }
            if (model.getCurday() ==0){
                model.setCurday(DateUtil.getIntYesterday());
            }
            dataList = statisticsOutChannelService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, StatisticsOutChannelModel model) throws Exception {
        StatisticsOutChannelModel data = new StatisticsOutChannelModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            }else{
                //不是管理员，不能操作
                HtmlUtil.writerJson(response, data);
                log.info("4");
                return;
            }
            if (model.getCurday() == 0){
                model.setCurday(DateUtil.getIntYesterday());
            }
            data = statisticsOutChannelService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }

}
