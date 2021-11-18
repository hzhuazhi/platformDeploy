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
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" >
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>
                <div class = "condQueryLabelDiv">拉单金额：</div>
                <div class="formCtrlDiv" >
                    <input type ="text" class ="inputCommonSty" id="money" name ="money">
                </div>

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>

                <div class = "condQueryLabelDiv">结束时间：</div>
                <div class="formCtrlDiv" >
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}"/>
                </div>
            </div>

            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">审核状态：</div>
                <div class="formCtrlDiv">
                    <select id="workType" name="workType">
                        <option value="0">===请选择===</option>
                        <option value="1">初始化</option>
                        <option value="2">废弃</option>
                        <option value="3">通过</option>
                    </select>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>

                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="拉单充值" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="200">订单号</th>
            <th width="200">名称/别名</th>
            <th width="200">渠道名称</th>
            <th width="150">拉单金额</th>
            <th width="150">金额类型</th>
            <%--<th width="150">是否展现</th>--%>
            <th width="150">审核状态</th>
            <th width="200">创建时间</th>
            <th width="200">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>


<div id="show" style="display:none;width:500px;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red">拉单充值审核</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        渠道
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divChannelName" name="divChannelName" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divMyTradeNo" name="divMyTradeNo" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        名称/别名
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divAlias" name="divAlias" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        拉单金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divMoney" name="divMoney" disabled="disabled"/>
                    </div>
                </dd>



                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        创建时间
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divCreateTime" name="divCreateTime" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        <font color="red">*</font>提现状态
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="checkWorkType" id="checkWorkType">
                            <option value="0">==请选择==</option>
                            <option value="2">废弃</option>
                            <option value="3">通过</option>
                        </select>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        备注
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <textarea id="remark" name="remark" cols="40" rows="5"></textarea>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        说明
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <textarea id="dataExplain" name="dataExplain" cols="40" rows="5"></textarea>
                    </div>
                </dd>



                <dd style="border-top: none;">
                    <div class="formTextDiv"></div>
                    <%--<div class="formCtrlDiv">
                        -------------------------------------------------------------------------------
                    </div>--%>
                </dd>
                <dd style=" height: 60px; line-height: 58px;">
                    <div class="formCtrlDiv">
							<span style="margin-left: 100px;">
								<input type="submit" style="background-color: #767DC3" class="formBtn" value="保　存" />
								<%--<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />--%>
								<input type="reset" onClick="javascript :closeDialog('show')" style="background-color: #767DC3" class="formBtn" value=" 返 回 " />
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>

<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/preparerecharge/preparerecharge.js'></script>
</body>
</html>

<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
