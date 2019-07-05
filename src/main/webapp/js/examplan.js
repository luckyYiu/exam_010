function toaddExamPlan() {
    layer.open({
        type: 2,
        title: "添加待考信息",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '410px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'examPlan/preAddExamPlan.action', 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}

function addExamPlan(){
    var a = layer.load();
    var classId = $("#classId").val();
    var courseId = $("#courseId").val();
    var examPaperId = $("#examPaperId").val();
    var beginTime = $("#beginTime").val();

    $.ajax(
        {
            url:path+"examPlan/insertExamPlan.action",
            type:"POST",
            dataType:"json",
            data:{"classId":classId,"courseId":courseId,"examPaperId":examPaperId,"beginTime":beginTime},
            success:function(data){
                layer.close(a);
                if(data.state==0){
                    layer.msg(data.msg,function () {
                        layer.closeAll();
                        parent.window.layer.closeAll();
                    });
                }else{
                    layer.msg(data.msg,function () {

                    });
                }
            }
        }
    );
}

function toupdateExamPlan(examPlanId){
    layer.open({
        type: 2,
        title: "修改待考信息",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.5],
        area: ['340px', '450px'],
        /*offset: 'rb', //右下角弹出*/
        /* time: 2000, //2秒后自动关闭*/
        anim: 2,
        content: [path+'examPlan/preUpdateExamPlan.action?examPlanId='+examPlanId, 'no'], //iframe的url，no代表不显示滚动条
        end: function(){ //此处用于演示
            location.reload();
        }
    });
}
function updateExamPlan(){
    var a = layer.load();
    var classId = $("#classId").val();
    var courseId = $("#courseId").val();
    var examPaperId = $("#examPaperId").val();
    var beginTime = $("#beginTime").val();
    var examPlanId = $("#examPlanId").val();

    $.ajax(
        {
            url:path+"examPlan/updateExamPlan.action",
            type:"POST",
            dataType:"json",
            data:{"classId":classId,"courseId":courseId,"examPaperId":examPaperId,"beginTime":beginTime,"examPlanId":examPlanId},
            success:function(data){
                layer.close(a);
                if(data.state==0){
                    layer.msg(data.msg,function () {
                        layer.closeAll();
                        parent.window.layer.closeAll();
                    });
                }else{
                    layer.msg(data.msg,function () {

                    });
                }
            }
        }
    );
}

function deleteExamPlan(examPlanId) {
    layer.confirm('是否确定要删除？', {
        btn: ['是','否'] //按钮
    }, function(){
        var a = layer.load();
        $.ajax(
            {
                url:path+"examPlan/deleteExamPlan.action",
                type:"POST",
                dataType:"json",
                data:{"examPlanId":examPlanId},
                success:function(data){
                    layer.close(a);
                    if(data.state==0){
                        layer.msg(data.msg,function () {
                            layer.closeAll();
                            location.reload();
                        });
                    }else{
                        layer.msg(data.msg,function () {

                        });
                    }
                }
            }
        );
    }, function(){
        layer.closeAll();
    });
}