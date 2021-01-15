
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/admingewaychange/list.do',
        dataList_url : ctx + "/admingewaychange/dataList.do",
        add_url : ctx+ "/admingewaychange/add.do",
        update_url : ctx+ "/admingewaychange/update.do",
        queryId_url: ctx+ "/admingewaychange/getId.do",
        delete_url: ctx+ "/admingewaychange/delete.do",
        manyOperation_url: ctx+ "/admingewaychange/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"orderNo",},
        {"data":"money",},
        {"data":"changeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.changeType==1){
                    html='<span><font color="red">减金额</font></span>';
                }else if(oData.changeType==2){
                    html+="<span >加金额</span>"
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
                    html='<span><font color="red">不展现</font></span>';
                }else if(oData.isShow==0){
                    html+="<span >初始化</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"dataExplain",},
        {"data":"createTime",}
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
        this.queryGewayAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/admingewaychange/jumpAdd.do";
        });

        // this.queryTotal();
        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayId'] = $("#gewayId").val();
            account.condJsonData['changeType'] = $("#changeType").val();
            account.condJsonData['isShow'] = $("#isShow").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayId'] = "";
            account.condJsonData['changeType'] = "";
            account.condJsonData['isShow'] = "";
            account.condJsonData['curdayEnd'] = "";
            account.condJsonData['changeType'] = "";
            $("#gewayId").val("0");
            $("#changeType").val("0");
            $("#isShow").val("0");
            $("#curdayStart").val("");
            $("#curdayEnd").val("");
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
    //查询所有通道-无分页-下拉框选项:
    queryGewayAll:function(){
        var url = ctx + "/geway/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayId' name='gewayId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayName+"</option>";
            }
            shtml +="</select>";
            $("#gewayDiv").html(shtml);
        });
    },



}

$(function(){
    account.indexInit();
})
