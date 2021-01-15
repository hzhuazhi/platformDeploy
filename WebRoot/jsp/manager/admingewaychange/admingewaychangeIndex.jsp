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
        <form id = "condForm" style="width: 100%">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">通道：</div>
                <div class="formCtrlDiv" id = "gewayDiv">

                </div>
                <div class = "condQueryLabelDiv">变更类型：</div>
                <div class="formCtrlDiv">
                    <select id="changeType" name="changeType">
                        <option value="0">===请选择===</option>
                        <option value="1">减金额</option>
                        <option value="2">加金额</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">是否展现：</div>
                <div class="formCtrlDiv">
                    <select id="isShow" name="isShow">
                        <option value="0">===请选择===</option>
                        <option value="1">展现</option>
                        <option value="2">不展现</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>

                <div class = "condQueryLabelDiv">结束时间：</div>
                <div class="formCtrlDiv" >
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}"/>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>

                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
            </div>

        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="130">通道名称</th>
            <th width="120">订单号</th>
            <th width="120">变更金额</th>
            <th width="120">变更类型</th>
            <th width="120">是否展现</th>
            <th width="200">备注</th>
            <th width="200">数据说明</th>
            <th width="120">创建时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/admingewaychange/admingewaychange.js'></script>
</body>
</html>
