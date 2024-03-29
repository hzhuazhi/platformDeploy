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
    <c:if test="${op==2}">
        <div class="formHeadDiv">
            <h2>重置密码</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="2">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>新密码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd" name="passWd" value=""	maxlength="240" />
                            <span id="msg"></span>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>确认密码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd1" name="passWd1" value="" onkeyup="validate()"	maxlength="240" />
                            <span id="msg1"></span>
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
    </c:if>
    <c:if test="${op==1}">
        <div class="formHeadDiv">
            <h2>编辑账号</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="1">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>账号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountNum" name="accountNum" value="${dl.accountNum}"	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>角色</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="type" name="roleId">
                                <option value="">渠道</option>
                            </select>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>渠道名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="channelName" name="channelName" value="${dl.channelName}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>渠道号/商铺号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="channel" name="channel" value="${dl.channel}" disabled maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>秘钥</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="secretKey" name="secretKey" value="${dl.secretKey}" maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>开通google密钥</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isGoogle"  name ="isGoogle">
                                <c:if test="${dl.isGoogle == 1}">
                                    <option value="1" selected="selected">不需要</option>
                                    <option value="2">需要</option>
                                </c:if>
                                <c:if test="${dl.isGoogle == 2}">
                                    <option value="1" >不需要</option>
                                    <option value="2" selected="selected">需要</option>
                                </c:if>
                            </select>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">公司名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="companyName" name="companyName" value="${dl.companyName}" 	maxlength="240" />
                        </div>
                    </li>



                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">联系人</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="contacts" name="contacts" value="${dl.contacts}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">联系电话</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="phoneNum" name="phoneNum" value="${dl.phoneNum}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">同步地址</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="lowerUrl" name="lowerUrl" value="${dl.lowerUrl}" 	maxlength="500" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">同步标识</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="lowerSuc" name="lowerSuc" value="${dl.lowerSuc}" 	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">白名单IP</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="whiteListIp" name="whiteListIp" value="${dl.whiteListIp}"	maxlength="240" />
                        </div>
                    </li>


                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>提现类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="withdrawType" name="withdrawType">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.withdrawType == 1}">
                                    <option value="1" selected="selected">平台内</option>
                                    <option value="2">平台外</option>
                                </c:if>
                                <c:if test="${dl.withdrawType == 2}">
                                    <option value="1">平台内</option>
                                    <option value="2" selected="selected">平台外</option>
                                </c:if>
                            </select>
                        </div>
                    </li>


                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>渠道类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="channelType" name="channelType">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.channelType == 1}">
                                    <option value="1" selected="selected">代收</option>
                                    <option value="2">大包</option>
                                    <option value="3">代付</option>
                                </c:if>
                                <c:if test="${dl.channelType == 2}">
                                    <option value="1">代收</option>
                                    <option value="2" selected="selected">大包</option>
                                    <option value="3">代付</option>
                                </c:if>
                                <c:if test="${dl.channelType == 3}">
                                    <option value="1">代收</option>
                                    <option value="2">大包</option>
                                    <option value="3" selected="selected">代付</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>是否同步</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isSynchro" name="isSynchro">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.isSynchro == 1}">
                                    <option value="1" selected="selected">需要同步</option>
                                    <option value="2">无需同步</option>
                                </c:if>
                                <c:if test="${dl.isSynchro == 2}">
                                    <option value="1" >需要同步</option>
                                    <option value="2" selected="selected">无需同步</option>
                                </c:if>
                            </select>
                        </div>
                    </li>


                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>数据发送类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="sendDataType" name="sendDataType">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.sendDataType == 1}">
                                    <option value="1" selected="selected">get</option>
                                    <option value="2" >post/form</option>
                                    <option value="3" >post/json</option>
                                </c:if>
                                <c:if test="${dl.sendDataType == 2}">
                                    <option value="1" >get</option>
                                    <option value="2" selected="selected">post/form</option>
                                    <option value="3" >post/json</option>
                                </c:if>
                                <c:if test="${dl.sendDataType == 3}">
                                    <option value="1" >get</option>
                                    <option value="2" >post/form</option>
                                    <option value="3" selected="selected">post/json</option>
                                </c:if>
                            </select>
                        </div>
                    </li>


                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" style="background-color: #1094fa" class="formBtn" value="修 改" /> <span>
						</span> <input type="reset" style="background-color: #1094fa" class="formBtn" value="重  置" />
                            <input type="button" style="background-color: #1094fa" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </c:if>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                accountNum:{
                    required:true,
                    maxlength:20
                },
                passWd:{
                    required:true,
                    maxlength:20
                },
                passWd1:{
                    required:true,
                    maxlength:20,
                    equalTo: "#passWd"
                },
                channelType:{
                    required:true
                }
            },
            messages: {
                accountNum:{
                    required : "账号不能为空!",
                    maxlength : "账号长度最多是20个字符!"
                },
                passWd:{
                    required : "新密码不能为空!",
                    maxlength : "新密码长度最多是20个字符!"
                },
                passWd1:{
                    required : "确认密码不能为空!",
                    maxlength : "新密码长度最多是20个字符!",
                    equalTo: "两次密码输入不一致!"
                },
                channelType:{
                    required:"渠道类型不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/accounttp/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/accounttp/list.do";
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
            }
        });
    });
</script>
</body>
</html>