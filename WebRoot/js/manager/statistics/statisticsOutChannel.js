
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/statisticsOutChannel/list.do',
        dataList_url : ctx + "/statisticsOutChannel/dataList.do",
        add_url : ctx+ "/statisticsOutChannel/add.do",
        update_url : ctx+ "/statisticsOutChannel/update.do",
        queryId_url: ctx+ "/statisticsOutChannel/getId.do",
        delete_url: ctx+ "/statisticsOutChannel/delete.do",
        manyOperation_url: ctx+ "/statisticsOutChannel/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"curday",},
        {"data":"channelName",},
        {"data":"balance",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.balance +'</font></span>';
                $(nTd).html(html);
            }
        },
        // {"data":"lockMoney",},
        {"data":"totalAmount",},
        {"data":"actualMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.actualMoney +'</font></span>';
                $(nTd).html(html);
            }
        },
        {"data":"addMoney",},
        {"data":"reduceMoney",}


    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        curday:0,
        channelId:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 查询条件 - 下拉框数据获取
        this.queryChannelAll();
        this.queryTotal();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['curday'] = $("#curday").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['curday'] = "";
            $("#curday").val("");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

    },


    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryChannelAll:function(){
        var url = basePath + "accounttp/dataAllList.do";
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



    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "statisticsOutChannel/totalData.do";
        var curday = $("#curday").val();
        var channelId = $("#channelId").val();

        var data = {
            "curday":curday,
            "channelId":channelId
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         ";
            shtml += "      余额：";
            shtml += "<font color='red'>" + data.totalBalance + "</font>";
            // shtml += "      锁定金额：";
            // shtml += "<font color='red'>" + data.totalLockMoney + "</font>";
            shtml += "      跑量金额：";
            shtml += "<font color='red'>" + data.totalTotalAmount + "</font>";
            shtml += "      实际金额：";
            shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      加金额：";
            shtml += "<font color='red'>" + data.totalAddMoney + "</font>";
            shtml += "      减金额：";
            shtml += "<font color='red'>" + data.totalReduceMoney + "</font>";
            $("#totalDiv").html(shtml);
        });
    },







}

$(function(){
    account.indexInit();
})
