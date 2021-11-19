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


                    <div class = "condQueryLabelDiv">代理：</div>
                    <div class="formCtrlDiv" id = "agentDiv">
                    </div>


                    <div class = "condQueryLabelDiv">分成类型：</div>
                    <div class="formCtrlDiv" id = "moduleTypeDiv">
                        <select id="profitType" name="profitType" class='text-input medium-input'>
                            <option value="0" selected="selected">=请选择=</option>
                            <option value="1">固定分成</option>
                            <option value="2">额外分成</option>
                        </select>
                    </div>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>
                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>

            </div>
        </form>
    </div>

    <table class="datatable tables">

        <thead>
        <div class="formCtrlDiv" id = "totalDiv">

        </div>
        <%--<c:set var="total" value="${total}"/>--%>
        <%--汇总：${total.totalMoney}---${total.totalServiceCharge}---${total.totalActualMoney}--%>
        <tr>
            <th width="110">代理名称</th>
            <th width="150">平台订单</th>
            <th width="100">订单金额</th>
            <th width="130">实际支付金额</th>
            <%--<th width="100">手续费</th>--%>
            <th width="100">分成类型</th>
            <th width="100">收益分成</th>
            <th width="100">收益</th>
            <th width="150">创建时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/agentdata/agentdata.js'></script>
</body>
</html>
