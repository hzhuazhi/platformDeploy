<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <style type="text/css">
        .manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
        .manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
        .manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
    <div class="formHeadDiv">
        <h2>编辑批次号</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dataModel" value="${batchModel}"/>
                <input type="hidden" id="id" name="id" value="${dataModel.id}">
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>渠道</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="tpId" name="tpId" onchange="queryChannelAll()">
                            <option value="">===请选择===</option>
                            <c:forEach items="${tpList}" var="dataList">
                                <c:choose>
                                    <c:when test="${dataModel.tpId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.acName}</option>
                                    </c:when>
                                    <c:when test="${dataModel.tpId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.acName}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>渠道号名称</span>
                    </div>
                    <div class="formCtrlDiv" id = "channelDiv">
                        <select id="channelId" name="channelId">
                            <option value="">===请选择===</option>
                            <c:forEach items="${chList}" var="dataList">
                                <c:choose>
                                    <c:when test="${dataModel.channelId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.channelName}</option>
                                    </c:when>
                                    <c:when test="${dataModel.channelId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.channelName}</option>
                                    </c:when>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>批次号名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="batchName" name="batchName" value="${dataModel.batchName}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>批次号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="batchNum" name="batchNum" value="${dataModel.batchNum}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="remark" name="remark" value="${dataModel.remark}" 	maxlength="240" />
                    </div>
                </li>
                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="修 改" /> <span>
                    </span> <input type="reset" class="formBtn" value="重  置" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                tpId:{
                    required:true,
                    maxlength:20
                },
                channelId:{
                    required:true,
                    maxlength:20
                },
                batchName:{
                    required:true,
                    maxlength:50
                },
                batchNum:{
                    required:true,
                    maxlength:20
                },
            },
            messages: {
                tpId:{
                    required : "请选择渠道!"
                },
                channelId:{
                    required : "请选择渠道号名称!",
                },
                batchName:{
                    required:"批次号名称不能为空!",
                    maxlength:"批次号名称长度最多是50个字符!"
                },
                batchNum:{
                    required:"批次号不能为空!",
                    maxlength:"批次号长度最多是20个字符!"
                },
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/batch/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/batch/list.do";
                        } else {
                            art.alert(data.msg);
                        }
                    },
                    error : function(data) {
                        art.alert(data.info);
                    }
                });
                return false;
                //阻止表单提交
            },

        });

    });

    //下拉框数据填充
    //查询所有渠道号数据-无分页-下拉框选项:
    var queryChannelAll = function(){
        var url = ctx + "/ch/dataAllList.do";
        var tpId = $("#tpId").val();
        var data = {
            "tpId":tpId
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    }
</script>
</body>
</html>