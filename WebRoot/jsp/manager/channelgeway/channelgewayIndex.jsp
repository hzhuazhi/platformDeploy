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
                <div class = "condQueryLabelDiv">关联名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="linkName" name ="linkName">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>
                <div class = "condQueryLabelDiv">通道：</div>
                <div class="formCtrlDiv" id = "gewayDiv">
                </div>

                <div class = "condQueryLabelDiv">手续费类型：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="serviceChargeType" name="serviceChargeType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">固定手续费</option>
                        <option value="2">额外手续费</option>
                    </select>
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="添加关联关系" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="200">关联名称</th>
            <th width="150">渠道名称</th>
            <th width="150">通道名称</th>
            <th width="150">手续费类型</th>
            <th width="150">固定手续费</th>
            <th width="150">额外手续费</th>
            <th width="120">扣量比例</th>
            <th width="120">利益类型</th>
            <th width="120">代码类型</th>
            <th width="80">上限</th>
            <th width="120">限量金额</th>
            <th width="120">筛选比例</th>
            <th width="150">备注</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/channelgeway/channelgeway.js'></script>
</body>
</html>
