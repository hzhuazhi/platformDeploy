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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/gy/gy.js'></script>
</body>
</html>
