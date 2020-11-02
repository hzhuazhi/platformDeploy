<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">平台订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">
                </div>
                <div class = "condQueryLabelDiv">商家订单号：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" >
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>
                <div class = "condQueryLabelDiv">订单金额：</div>
                <div class="formCtrlDiv" >
                    <input type ="text" class ="inputCommonSty" id="totalAmount" name ="totalAmount">
                </div>

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="beginTime" id="beginTime" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>

                <div class = "condQueryLabelDiv">结束时间：</div>
                <div class="formCtrlDiv" >
                    <input type="text" class ="inputCommonSty" name="endTime" id="endTime" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>
        </form>
    </div>

    <div id="divCount">
        <strong> 汇总：</strong>
        &nbsp;  <strong> 总金额：</strong><strong style="color:#bb0000 " id="countTotalMoney"></strong>
        &nbsp;  <strong> 订单成功金额：</strong><strong style="color:#bb0000 " id="successCountMoney"></strong>
        &nbsp;  <strong> 成功率：</strong><strong style="color:#bb0000 " id="successRate"></strong>
        &nbsp;  <strong> 成功手续费：</strong><strong style="color:#bb0000 " id="successServiceChargeMoney"></strong>
        <%--&nbsp;  <strong> 订单成功金额：</strong><strong style="color:#bb0000 " id="successCountMoney"></strong>--%>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="200">平台订单</th>
            <th width="200">商家订单号</th>
            <th width="150">渠道</th>
            <th width="150">订单金额</th>
            <th width="150">手续费</th>
            <th width="150">实际金额</th>
            <th width="150">请求状态</th>
            <th width="150">交易状态</th>
            <th width="250">交易时间</th>
            <%--<th width="250">补单类型</th>--%>
            <th width="250">同步状态</th>
            <th width="250">操作</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/channelout/channelout.js'></script>
</body>
</html>
