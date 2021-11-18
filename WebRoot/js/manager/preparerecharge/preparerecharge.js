
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/preparerecharge/list.do',
        dataList_url : ctx + "/preparerecharge/dataList.do",
        add_url : ctx+ "/preparerecharge/add.do",
        update_url : ctx+ "/preparerecharge/update.do",
        queryId_url: ctx+ "/preparerecharge/getId.do",
        delete_url: ctx+ "/preparerecharge/delete.do",
        manyOperation_url: ctx+ "/preparerecharge/manyOperation.do",
        check_url: ctx+ "/preparerecharge/check.do"
    },


    //添加修改验证参数
    validate:{
        submitHandler : function() {
            var id = $("#show input[type='hidden']").val();
            var url = "";
            if(id){
                url = account.url.check_url;
            }

            var formData = $("#newFirstStoreForm").serialize();
            $.ajax({
                url : url,
                type : 'post',
                dataType : 'json',
                data :formData,
                success : function(data) {
                    if(data.success){
                        promptMessage ('保存成功！','success',true);
                        common.goList();
                    }else{
                        promptMessage(data.msg, 'warning', false);
                    }

                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
            return false;
            //阻止表单提交
        }
    },



    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"alias",},
        {"data":"channelName",},
        {"data":"money",},
        {"data":"changeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.changeType==1){
                    html+="<span >核减金额</span>"
                }else if(oData.changeType==2){
                    html+="<span >加金额</span>"
                }else if(oData.changeType==0){
                    html+="<span >初始化</span>"
                }
                $(nTd).html(html);
            }
        },
        // {"data":"isShow",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html = '';
        //         if(oData.isShow==1){
        //             html+="<span >展现</span>"
        //         }else if(oData.isShow==2){
        //             html+="<span >不展现</span>"
        //         }else if(oData.isShow==0){
        //             html+="<span >初始化</span>"
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"workType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.workType==1){
                    html='<span><font color="red">初始化</font></span>';
                }else if(oData.workType==2){
                    html+="<span >废弃</span>"
                }else if(oData.workType==3){
                    html='<span><font color="green">通过</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.workType == 1){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 未审核 </a>'
                }else {
                    html += '已审核';
                }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        channelName:null,
        money:null,
        curdayStart:0,
        curdayEnd:0,
        workType:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/preparerecharge/jumpAdd.do";
        });

        // this.queryTotal();
        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {

            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['money'] = $("#money").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.condJsonData['workType'] = $("#workType").val();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            account.condJsonData['channelName'] = "";
            account.condJsonData['money'] = "";
            account.condJsonData['curdayStart'] = "0";
            account.condJsonData['curdayEnd'] = "0";
            account.condJsonData['workType'] = "0";

            $("#myTradeNo").val("");
            $("#channelName").val("");
            $("#money").val("");
            $("#curdayStart").val("0");
            $("#curdayEnd").val("0");
            $("#workType").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

        //删除
        $(".dataTableResetBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });





        //审核
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/preparerecharge/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        common.addInit(account.validate);
                        $("#id").val(id);
                        $("#divChannelName").val(m.channelName);
                        $("#divMyTradeNo").val(m.myTradeNo);
                        $("#divAlias").val(m.alias);
                        $("#divMoney").val(m.money);
                        $("#divCreateTime").val(m.createTime);
                        $("#remark").val(m.remark);
                        $("#dataExplain").val(m.dataExplain);

                        openDialog("show","");
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });



    },


    // //下拉框数据填充
    // //查询所有渠道-无分页-下拉框选项:
    // queryTpAll:function(){
    //     var url = ctx + "/channelchange/dataAllList.do";
    //     var data = {
    //     };
    //     common.ajax(url,data,function(data){
    //         var dataList=data;
    //         var shtml="";
    //         shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
    //         shtml +="<option value=''>===请选择===</option>";
    //         for (var i=0;i<dataList.length>0;i++) {
    //             shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
    //         }
    //         shtml +="</select>";
    //         $("#channelDiv").html(shtml);
    //     });
    // },



}
//
// function  repeat(id){
//     let  data={
//         "id":id
//     }
//
//     $.ajax({
//         url : ctx+ "/channelchange/manyOperation.do",
//         type : 'post',
//         dataType : 'json',
//         data :data,
//         success : function(data) {
//             if (data.success) {
//                 alert("修改成功！");
//                 window.location.href = ctx + "/channelOut/list.do";
//             } else {
//                 alert(data.msg);
//             }
//         },
//         error : function(data) {
//             alert(data.info);
//         }
//     });
//
// }

$(function(){
    account.indexInit();
})
