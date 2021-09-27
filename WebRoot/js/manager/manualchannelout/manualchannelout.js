
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/manualchannelout/list.do',
        dataList_url : ctx + "/manualchannelout/dataList.do",
        // add_url : ctx+ "/tpDataInfo/add.do",
        // update_url : ctx+ "/tpDataInfo/update.do",
        // queryId_url: ctx+ "/tpDataInfo/getId.do",
        // delete_url: ctx+ "/tpDataInfo/delete.do",
        manyOperation_url: ctx+ "/manualchannelout/manyOperation.do",
    },


    //页面加载
    indexInit : function (){
        // queryChannelGewayByChannel();

    },


}


// 根据渠道获取通道
function queryChannelGewayByChannel(){
    var url = basePath + "channelgeway/queryChannelgewayByChannel.do";
    var channelId = $("#channelId").val();
    var data = {
        "channelId":channelId
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml += "<select id='tradeType' name='tradeType'  class='text-input medium-input'>";
        shtml +="<option value=''>===请选择===</option>";
        for (var i=0;i<dataList.length>0;i++) {
            shtml +="<option value="+dataList[i].myTradeType+">==="+dataList[i].gewayName+"==="+dataList[i].myTradeType+"</option>";
        }
        shtml +="</select>";
        $("#gewayDiv").html(shtml);
    });
}


$(function(){
    account.indexInit();
    // this.queryChannelGewayByChannel();
})
