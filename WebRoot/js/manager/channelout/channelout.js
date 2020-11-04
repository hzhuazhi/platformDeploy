
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelOut/list.do',
        dataList_url : ctx + "/channelOut/dataList.do",
        add_url : ctx+ "/channelOut/add.do",
        update_url : ctx+ "/channelOut/update.do",
        queryId_url: ctx+ "/channelOut/getId.do",
        delete_url: ctx+ "/channelOut/delete.do",
        manyOperation_url: ctx+ "/channelOut/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"actualMoney",},
        {"data":"sendOk",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.sendOk==1){
                    html+="<span >成功</span>"
                }else if(oData.sendOk==2){
                    html+="<span >失败</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.orderStatus==1){
                    html+="<span >初始化</span>"
                }else if(oData.orderStatus==2){
                    html+="<span >失败</span>"
                }else if(oData.orderStatus==3){
                    html+="<span >失败</span>"
                }else if(oData.orderStatus==4){
                    html+="<span style='color: #bb0000'>成功</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"sendStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.sendStatus==1){
                    html+="<span >成功</span>";
                }else if(oData.sendStatus==2){
                    html+="<span style='color: #bb0000'>失败</span>";
                }else if(oData.sendStatus==3){
                    html+="<span style='color: #bb0000'>失败</span>";
                }else if(oData.sendStatus==4){
                    html+="<span style='color: #4aff1a'>成功</span>";
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.sendStatus==2){
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " onclick="repeat('+oData.id+')"> 重发 </a>';
                }

                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        beginTime:0,
        endTime:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelOut/jumpAdd.do";
        });

        this.queryTotal();
        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['totalAmount'] = $("#totalAmount").val();
            account.condJsonData['beginTime'] = $("#beginTime").val();
            account.condJsonData['endTime'] = $("#endTime").val();
            account.condJsonData['sendOk'] = $("#sendOk").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['sendStatus'] = $("#sendStatus").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            account.condJsonData['outTradeNo'] = "";
            account.condJsonData['channelName'] = "";
            account.condJsonData['totalAmount'] = "";
            account.condJsonData['beginTime'] = "";
            account.condJsonData['endTime'] = "";
            account.condJsonData['sendOk'] = "";
            account.condJsonData['orderStatus'] = "";
            account.condJsonData['sendStatus'] = "";
            $("#myTradeNo").val("");
            $("#outTradeNo").val("");
            $("#channelName").val("");
            $("#totalAmount").val("");
            $("#beginTime").val("0");
            $("#endTime").val("0");
            $("#sendOk").val("0");
            $("#orderStatus").val("0");
            $("#sendStatus").val("0");
            this.queryTotal();
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

    },

    queryTotal:function (){
        var url = ctx + "/channelOut/totalData.do";
        var myTradeNo= $("#myTradeNo").val();
        var outTradeNo= $("#outTradeNo").val();
        var channelName= $("#channelName").val();
        var totalAmount= $("#totalAmount").val();
        var beginTime= $("#beginTime").val();
        var endTime= $("#endTime").val();
        var sendOk= $("#sendOk").val();
        var orderStatus= $("#orderStatus").val();
        var sendStatus= $("#sendStatus").val();



        var data = {
            "myTradeNo":myTradeNo,
            "outTradeNo":outTradeNo,
            "channelName":channelName,
            "totalAmount":totalAmount,
            "beginTime":beginTime,
            "sendOk":sendOk,
            "orderStatus":orderStatus,
            "sendStatus":sendStatus,
            "endTime":endTime
        };
        common.ajax(url,data,function(data){
            var data=data;

            $("#countTotalMoney").html(data.countTotalMoney);
            $("#successCountMoney").html(data.successCountMoney);
            $("#successRate").html(data.successRate);
            $("#successServiceChargeMoney").html(data.successServiceChargeMoney);
        });
    },

    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryTpAll:function(){
        var url = ctx + "channelout/dataAllList.do";
        var data = {
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
    },



}

function  repeat(id){
    let  data={
        "id":id
    }

    $.ajax({
        url : ctx+ "/channelOut/manyOperation.do",
        type : 'post',
        dataType : 'json',
        data :data,
        success : function(data) {
            if (data.success) {
                alert("修改成功！");
                window.location.href = ctx + "/channelOut/list.do";
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert(data.info);
        }
    });

}

$(function(){
    account.queryTotal();
    account.indexInit();
})
