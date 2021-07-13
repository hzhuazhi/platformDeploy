
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/geway/list.do',
        dataList_url : ctx + "/geway/dataList.do",
        add_url : ctx+ "/geway/add.do",
        update_url : ctx+ "/geway/update.do",
        queryId_url: ctx+ "/geway/getId.do",
        delete_url: ctx+ "/geway/delete.do",
        manyOperation_url: ctx+ "/geway/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        {"data":"roleName",},
        {"data":"gewayName",},
        {"data":"totalMoney",},
        {"data":"leastMoney",},
        {"data":"balance",},
        {"data":"gewayType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.gewayType==1){
                    html='<span>普通通道</span>';
                }else if(oData.gewayType==2){
                    html='<span><font color="red">预付通道</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"moneyType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.moneyType==1){
                    html='<span>固定的</span>';
                }else if(oData.moneyType==2){
                    html='<span>单一范围</span>';
                }else if(oData.moneyType==3){
                    html='<span>多个范围</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"moneyRange",},


        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/geway/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +'<a class = "dataTableBtn" href="'+ctx+'/geway/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/geway/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayName'] = $("#gewayName").val();
            account.condJsonData['gewayType'] = $("#gewayType").val();
            account.condJsonData['companyName'] = $("#companyName").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayName'] = "";
            $("#gewayName").val("");
            account.condJsonData['gewayType'] = "";
            $("#gewayType").val("");
            account.condJsonData['companyName'] = "";
            $("#companyName").val("");
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

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });
    }

}

$(function(){
    account.indexInit();
})
