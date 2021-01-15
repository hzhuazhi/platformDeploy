
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/gewaychange/list.do',
        dataList_url : ctx + "/gewaychange/dataList.do",
        add_url : ctx+ "/gewaychange/add.do",
        update_url : ctx+ "/gewaychange/update.do",
        queryId_url: ctx+ "/gewaychange/getId.do",
        delete_url: ctx+ "/gewaychange/delete.do",
        manyOperation_url: ctx+ "/gewaychange/manyOperation.do"
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
                    html+="<span >提现</span>"
                }else if(oData.changeType==2){
                    html+="<span >充值</span>"
                }
                $(nTd).html(html);
            }
        },
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
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/gewaychange/jumpAdd.do";
        });

        // this.queryTotal();
        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['curdayEnd'] = "";
            account.condJsonData['changeType'] = "";
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




}

$(function(){
    account.indexInit();
})
