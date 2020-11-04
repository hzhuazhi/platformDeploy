
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelchange/list.do',
        dataList_url : ctx + "/channelchange/dataList.do",
        add_url : ctx+ "/channelchange/add.do",
        update_url : ctx+ "/channelchange/update.do",
        queryId_url: ctx+ "/channelchange/getId.do",
        delete_url: ctx+ "/channelchange/delete.do",
        manyOperation_url: ctx+ "/channelchange/manyOperation.do"
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
        {"data":"isShow",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isShow==1){
                    html+="<span >展现</span>"
                }else if(oData.isShow==2){
                    html+="<span >不展现</span>"
                }else if(oData.isShow==0){
                    html+="<span >初始化</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"dataExplain",},
        {"data":"createTime",},
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
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelchange/jumpAdd.do";
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
            account.condJsonData['changeType'] = $("#changeType").val();
            account.condJsonData['isShow'] = $("#isShow").val();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            account.condJsonData['channelName'] = "";
            account.condJsonData['money'] = "";
            account.condJsonData['curdayEnd'] = "";
            account.condJsonData['changeType'] = "";
            account.condJsonData['isShow'] = "";
            $("#myTradeNo").val("");
            $("#channelName").val("");
            $("#money").val("");
            $("#curdayStart").val("0");
            $("#curdayEnd").val("0");
            $("#changeType").val("0");
            $("#isShow").val("0");
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


    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryTpAll:function(){
        var url = ctx + "/channelchange/dataAllList.do";
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
