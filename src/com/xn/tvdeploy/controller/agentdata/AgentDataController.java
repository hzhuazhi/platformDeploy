package com.xn.tvdeploy.controller.agentdata;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Account;
import com.xn.tvdeploy.model.AgentChannelModel;
import com.xn.tvdeploy.model.AgentDataModel;
import com.xn.tvdeploy.model.AgentModel;
import com.xn.tvdeploy.service.AgentChannelService;
import com.xn.tvdeploy.service.AgentDataService;
import com.xn.tvdeploy.service.AgentService;
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
import java.util.Map;

/**
 * @Description 代理-订单数据-收益
 * @Author yoko
 * @Date 2020/5/6 0:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/agentdata")
public class AgentDataController extends BaseController {

    private static Logger log = Logger.getLogger(AgentDataController.class);

    @Autowired
    private AgentDataService<AgentDataModel> agentDataService;

    @Autowired
    private AgentChannelService<AgentChannelModel> agentChannelService;

    @Autowired
    private AgentService<AgentModel> agentService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/agentdata/agentdataIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AgentDataModel model) throws Exception {
        List<AgentDataModel> dataList = new ArrayList<AgentDataModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setAgentId(account.getId());
//                // 查询代理的收益提成
//                AgentModel agentModel = new AgentModel();
//                agentModel.setId(account.getId());
//                agentModel = agentService.queryById(agentModel);
//                model.setRoyalty(agentModel.getRoyalty());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", agentDataService.getTotalData(model));
            dataList = agentDataService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }







    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, AgentDataModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, AgentDataModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                model.setAgentId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<AgentDataModel> dataList = new ArrayList<AgentDataModel>();
            dataList = agentDataService.queryAllList(model);
            // 导出数据
            String[] titles = new String[9];
            String[] titleCode = new String[9];
            String filename = "代理收益信息";
//            titles = new String[]{"代理名称", "渠道名称", "平台订单", "订单金额", "实际支付金额", "手续费", "收益分成", "收益", "创建时间"};
//            titleCode = new String[]{"agentName", "channelName", "myTradeNo", "totalAmount", "payAmount", "serviceCharge", "profitRatio", "profit", "createTime"};
            titles = new String[]{"代理名称", "渠道名称", "平台订单", "订单金额", "实际支付金额", "收益分成", "收益", "创建时间"};
            titleCode = new String[]{"agentName", "channelName", "myTradeNo", "totalAmount", "payAmount", "profitRatio", "profit", "createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(AgentDataModel paramO : dataList){
                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }

    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, AgentDataModel model) throws Exception {
        AgentDataModel data = new AgentDataModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setAgentId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", agentDataService.getTotalData(model));
//            dataList = agentDataService.queryByList(model);
            data = agentDataService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }
}
