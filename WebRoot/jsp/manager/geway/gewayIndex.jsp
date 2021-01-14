<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>通道列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">通道名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="gewayName" name ="gewayName">
                </div>
                <div class = "condQueryLabelDiv">通道类型：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="gewayType" name ="gewayType">
                </div>
                <div class = "condQueryLabelDiv">公司名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="companyName" name ="companyName">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增通道" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="130">账号</th>
            <th width="100">角色</th>
            <th width="150">通道名称</th>
            <th width="120">总额</th>
            <th width="120">保底金额</th>
            <th width="90">余额</th>
            <th width="120">通道类型</th>
            <th width="250">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/geway/geway.js'></script>
</body>
</html>
