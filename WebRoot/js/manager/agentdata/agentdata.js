
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/agentdata/list.do',
        dataList_url : ctx + "/agentdata/dataList.do",
        // add_url : ctx+ "/tpDataInfo/add.do",
        // update_url : ctx+ "/tpDataInfo/update.do",
        // queryId_url: ctx+ "/tpDataInfo/getId.do",
        // delete_url: ctx+ "/tpDataInfo/delete.do",
        manyOperation_url: ctx+ "/agentdata/manyOperation.do"

    },
    //列表显示参数
    list:[
        {"data":"agentName",},
        {"data":"myTradeNo",},
        {"data":"totalAmount",},
        {"data":"payAmount",},
        // {"data":"serviceCharge",},
        {"data":"profitType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.profitType==1){
                    html='<span>固定分成</span>';
                }else if(oData.profitType==2){
                    html='<span><font color="red">额外分成</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"profitRatio",},
        {"data":"profit",},
        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        agentId:0,
        profitType:0,
        curdayStart:0,
        curdayEnd:0,
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryAgAll();
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['agentId'] = $("#agentId").val();
            account.condJsonData['profitType'] = $("#profitType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['agentId'] = "0";
            $("#agentId").val("0");
            account.condJsonData['profitType'] = "0";
            $("#profitType").val("0");
            account.condJsonData['curdayStart'] = "0";
            $("#curdayStart").val("0");
            account.condJsonData['curdayEnd'] = "0";
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.cf(data);
        });

        // // 数据按照Excel格式导出
        // $("#butExcelExport").click(function () {
        //     common.dataExportExcel($("#condForm"));
        // });
    },

    // //汇总数据填充
    // //查询所有订单汇总数据
    // queryTotal:function(){
    //     var url = basePath + "tpdata/totalData.do";
    //     var myTradeNo = $("#myTradeNo").val();
    //     var outTradeNo = $("#outTradeNo").val();
    //     var tradeStatus = $("#tradeStatus").val();
    //     var runStatus = $("#runStatus").val();
    //     var curdayStart = $("#curdayStart").val();
    //     var curdayEnd = $("#curdayEnd").val();
    //     var data = {
    //         "myTradeNo":myTradeNo,
    //         "outTradeNo":outTradeNo,
    //         "tradeStatus":tradeStatus,
    //         "runStatus":runStatus,
    //         "curdayStart":curdayStart,
    //         "curdayEnd":curdayEnd
    //     };
    //     common.ajax(url,data,function(data){
    //         var data=data;
    //         var shtml="";
    //         shtml += "汇总：         订单金额：";
    //         shtml += "<font color='red'>" + data.totalMoney + "</font>";
    //         shtml += "      手续费：";
    //         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
    //         shtml += "      实际金额：";
    //         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
    //         $("#totalDiv").html(shtml);
    //     });
    // }

    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "agentdata/totalData.do";
        var agentId = $("#agentId").val();
        // var channelName = $("#channelName").val();
        var profitType = $("#profitType").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "agentId":agentId,
            // "channelName":channelName,
            "profitType":profitType,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            // shtml += "      手续费：";
            // shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
            // shtml += "      实际金额：";
            // shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      收益：";
            shtml += "<font color='red'>" + data.totalProfit + "</font>";
            $("#totalDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有代理旗下的渠道-无分页-下拉框选项:
    queryTpAll:function(){
        var url = basePath + "agentchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].channelId+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },


    //下拉框数据填充
    //查询代理-无分页-下拉框选项:
    queryAgAll:function(){
        var url = basePath + "accountagent/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].agentName+"</option>";
            }
            shtml +="</select>";
            $("#agentDiv").html(shtml);
        });
    },

}

// function outQueryTotal(){
//     var url = basePath + "tpdata/totalData.do";
//     var myTradeNo = $("#myTradeNo").val();
//     var outTradeNo = $("#outTradeNo").val();
//     var tradeStatus = $("#tradeStatus").val();
//     var runStatus = $("#runStatus").val();
//     var curdayStart = $("#curdayStart").val();
//     var curdayEnd = $("#curdayEnd").val();
//     var data = {
//         "myTradeNo":myTradeNo,
//         "outTradeNo":outTradeNo,
//         "tradeStatus":tradeStatus,
//         "runStatus":runStatus,
//         "curdayStart":curdayStart,
//         "curdayEnd":curdayEnd
//     };
//     common.ajax(url,data,function(data){
//         var data=data;
//         var shtml="";
//         shtml += "汇总：         订单金额：";
//         shtml += "<font color='red'>" + data.totalMoney + "</font>";
//         shtml += "      手续费：";
//         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
//         shtml += "      实际金额：";
//         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
//         $("#totalDiv").html(shtml);
//     });
// }

$(function(){
    account.indexInit();
})
